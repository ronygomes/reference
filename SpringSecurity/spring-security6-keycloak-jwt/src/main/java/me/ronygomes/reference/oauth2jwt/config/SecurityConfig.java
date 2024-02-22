package me.ronygomes.reference.oauth2jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private static final String OAUTH2_CLIENT_ID = "webclient";

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.oauth2ResourceServer(rs ->
                rs.jwt(jwtResourceServer ->
                        jwtResourceServer.jwtAuthenticationConverter(authenticationConverter())));

        http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.csrf(AbstractHttpConfigurer::disable);

        http.exceptionHandling(eh -> eh.authenticationEntryPoint((request, response, authException) -> {
            response.addHeader(HttpHeaders.WWW_AUTHENTICATE, "Basic realm=\"Restricted Content\"");
            response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
        }));

        return http.build();
    }

    /* As Type information is lost in runtime, will throw error if exposed as @Bean
     * Alternative is remove lambda i.e. (jwt -> ..) and rewrite with class i.e. (new Converter<Jwt, JwtAuthenticationToken> { ... })
     * Source https://github.com/spring-projects/spring-boot/issues/24413
     */
    private Converter<Jwt, JwtAuthenticationToken> authenticationConverter() {

        return jwt -> new JwtAuthenticationToken(jwt, authoritiesConverter().convert(jwt),
                jwt.getClaimAsString(StandardClaimNames.PREFERRED_USERNAME));
    }

    private Converter<Jwt, List<SimpleGrantedAuthority>> authoritiesConverter() {
        /*
         * Keycloak Server JWT puts roles in following format. <client-id> is the placeholder for client-id
         *  "resource_access": {
         *    "<client-id>": {
         *      "roles": [ "user", "admin" ]
         *    }
         *  }
         */
        return jwt -> {
            final var resourceAccess = (Map<String, Object>) jwt.getClaims().getOrDefault("resource_access", Collections.emptyMap());
            final var clientAccess = (Map<String, Object>) resourceAccess.getOrDefault(OAUTH2_CLIENT_ID, Collections.emptyMap());
            final var clientRoles = (List<String>) clientAccess.getOrDefault("roles", Collections.emptyList());

            return clientRoles.stream()
                    .map(String::toUpperCase)
                    .map(SimpleGrantedAuthority::new).toList();
        };
    }
}

package me.ronygomes.reference.actuator.custom_endpoint;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration(proxyBeanMethods = false)
public class SecurityConfig {

    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";

    @Bean
    public SecurityFilterChain configureEndpoint(HttpSecurity security) throws Exception {
        security.securityMatcher(EndpointRequest.toAnyEndpoint());
        security.authorizeHttpRequests((req) -> req.anyRequest().hasAnyRole(ROLE_USER, ROLE_ADMIN));

        security.csrf(AbstractHttpConfigurer::disable);
        security.httpBasic(Customizer.withDefaults());
        security.sessionManagement(smc -> smc.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        security.exceptionHandling(ehc -> ehc.accessDeniedHandler(
                (req, res, ex) -> res.sendError(HttpStatus.NOT_FOUND.value(), ex.getMessage())));

        return security.build();
    }
}

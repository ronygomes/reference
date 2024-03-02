package me.ronygomes.reference.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

import java.util.UUID;

@Configuration
public class SpringSecurityConfig {

    private static final String DEFAULT_REALM_NAME = "Default Security App Realm";
    private static final String DIGEST_SECRET_KEY = UUID.randomUUID().toString();

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           DigestAuthenticationEntryPoint entryPoint,
                                           DigestAuthenticationFilter digestAuthenticationFilter) throws Exception {

        http.authorizeHttpRequests(auth -> auth.anyRequest().fullyAuthenticated());
        http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.csrf(AbstractHttpConfigurer::disable);

        http.exceptionHandling(e -> e.authenticationEntryPoint(entryPoint));
        http.addFilterBefore(digestAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public DigestAuthenticationEntryPoint digestAuthenticationEntryPoint() {
        DigestAuthenticationEntryPoint entryPoint = new DigestAuthenticationEntryPoint();
        entryPoint.setRealmName(DEFAULT_REALM_NAME);
        entryPoint.setKey(DIGEST_SECRET_KEY);

        return entryPoint;
    }

    @Bean
    public DigestAuthenticationFilter digestAuthenticationFilter(DigestAuthenticationEntryPoint entryPoint,
                                                                 UserDetailsService userDetailsService) {

        DigestAuthenticationFilter authFilter = new DigestAuthenticationFilter();
        authFilter.setUserDetailsService(userDetailsService);
        authFilter.setAuthenticationEntryPoint(entryPoint);

        // Doesn't work without this line as DigestAuthenticationFilter doesn't automatically create authentication token
        // Reference: https://stackoverflow.com/questions/75286514/
        authFilter.setCreateAuthenticatedToken(true);

        return authFilter;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}

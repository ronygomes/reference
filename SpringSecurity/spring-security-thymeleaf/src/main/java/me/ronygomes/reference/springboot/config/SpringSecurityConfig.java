package me.ronygomes.reference.springboot.config;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/", "/index", "/error").permitAll()
                .requestMatchers("/private").hasAnyRole("ADMIN", "USER")
                .anyRequest().denyAll()
        );

        http.csrf(Customizer.withDefaults());

        // Following line will configure DefaultLoginPageGeneratingFilter for login form
        // http.formLogin(Customizer.withDefaults());

        http.formLogin(login -> login
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/private")
                // Form Login failure will trigger 500 which will show Default Error Page
                // Not ideal but demonstrated for reference
                .failureHandler((req, res, ex) -> res.sendError(500))
        );

        http.logout(logout -> logout
                .logoutSuccessUrl("/index")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .permitAll()
        );

        http.rememberMe(remember -> remember
                .key("someRandomSecretKey")
                .tokenValiditySeconds(1000)
                .rememberMeCookieName("REMEMBER_LOGIN_COOKIE")
                .rememberMeParameter("remember-me")
        );

        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .maximumSessions(1)
        );

        // Will configure both formLogin and httpBasic
        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("123"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("12345"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /* This is required, otherwise SessionManagement -> maximumSessions(1) won't work
     * Defining as following will not work, as it's from Servlet API
     * @Bean
     * public HttpSessionEventPublisher httpSessionEventPublisher() {
     *   return new HttpSessionEventPublisher();
     * }
     */
    @Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher());
    }
}

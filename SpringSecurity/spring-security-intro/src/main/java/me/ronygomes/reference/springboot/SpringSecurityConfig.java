package me.ronygomes.reference.springboot;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {
    
    @Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // This will also disable login page, if httpBasic is commented
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());

        // Default login method is 'Login Form', not suitable for REST API
		http.httpBasic(withDefaults());

        // Disabled for REST API
		http.csrf().disable();

		return http.build();
	}
}

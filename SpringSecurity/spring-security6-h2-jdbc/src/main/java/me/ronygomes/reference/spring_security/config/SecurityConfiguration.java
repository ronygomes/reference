package me.ronygomes.reference.spring_security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/", "/index", "/error").permitAll()
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/private").hasAnyRole("ADMIN", "USER")
                .anyRequest().denyAll()
        );

        http.csrf(csrf ->
                csrf.ignoringRequestMatchers("/h2-console/**")
        );

        http.formLogin(Customizer.withDefaults());
        // Needed for H2 Console as it uses <iframe> which is prevented by default.
        http.headers(AbstractHttpConfigurer::disable);

        http.logout(logout -> logout
                .logoutSuccessUrl("/index")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .permitAll()
        );

        return http.build();
    }

    @Bean
    public EmbeddedDatabase embeddedDatabase(@Value("${application.datasource.name}") String databaseName) {
        return new EmbeddedDatabaseBuilder()
                .setName(databaseName)
                .setType(EmbeddedDatabaseType.H2)
                // Value: org/springframework/security/core/userdetails/jdbc/users.ddl
                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(EmbeddedDatabase embeddedDatabase,
                                                 PasswordEncoder passwordEncoder) {
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

        JdbcUserDetailsManager judm = new JdbcUserDetailsManager(embeddedDatabase);

        judm.createUser(user);
        judm.createUser(admin);

        return judm;
    }
}

/*
 * Filename: org/springframework/security/core/userdetails/jdbc/users.ddl
 *
 * create table users(
 *       username varchar_ignorecase(50) not null primary key,
 *       password varchar_ignorecase(500) not null,
 *       enabled boolean not null
 * );
 *
 * create table authorities (
 *       username varchar_ignorecase(50) not null,
 *       authority varchar_ignorecase(50) not null,
 *       constraint fk_authorities_users foreign key(username) references users(username)
 * );
 */
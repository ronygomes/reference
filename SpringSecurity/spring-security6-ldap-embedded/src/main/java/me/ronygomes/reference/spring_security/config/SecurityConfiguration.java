package me.ronygomes.reference.spring_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.ldap.LdapPasswordComparisonAuthenticationManagerFactory;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.server.UnboundIdContainer;
import org.springframework.security.ldap.userdetails.DefaultLdapAuthoritiesPopulator;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    private static final String USER_SEARCH_BASE = "ou=people";
    private static final String GROUP_SEARCH_BASE = "ou=groups";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(BaseLdapPathContextSource contextSource,
                                                       LdapAuthoritiesPopulator populator,
                                                       PasswordEncoder encoder) {

        LdapPasswordComparisonAuthenticationManagerFactory factory =
                new LdapPasswordComparisonAuthenticationManagerFactory(contextSource, encoder);

        factory.setUserDnPatterns("uid={0},ou=people");
        factory.setPasswordAttribute("userPassword");
        factory.setUserSearchBase(USER_SEARCH_BASE);
        factory.setLdapAuthoritiesPopulator(populator);

        return factory.createAuthenticationManager();
    }

    @Bean
    public LdapAuthoritiesPopulator authoritiesPopulator(BaseLdapPathContextSource contextSource) {
        DefaultLdapAuthoritiesPopulator populator =
                new DefaultLdapAuthoritiesPopulator(contextSource, GROUP_SEARCH_BASE);
        populator.setGroupSearchFilter("(member={0})");
        return populator;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    UnboundIdContainer ldapContainer() {
        return new UnboundIdContainer("dc=springframework,dc=org","classpath:users.ldif");
    }

    @Bean
    BaseLdapPathContextSource contextSource() {
        return new DefaultSpringSecurityContextSource("ldap://localhost:53389/dc=springframework,dc=org");
    }
}

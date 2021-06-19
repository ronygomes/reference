package me.ronygomes.reference.spring_ldap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.ldap.LdapAuthenticationProviderConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static java.util.Arrays.asList;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserExtraInfoContextMapper mapper;
    private final Environment environment;

    @Value("${app.config.ldap.username}")
    private String managerDn;

    @Value("${app.config.ldap.password}")
    private String managerPassword;

    @Value("${app.config.ldap.server.url}")
    private String ldapServerUrl;

    @Value("${app.config.ldap.userDn}")
    private String userDnPattern;

    @Value("${app.config.ldap.groupSearchBase}")
    private String groupSearchBase;

    @Autowired
    public SecurityConfig(UserExtraInfoContextMapper mapper, Environment environment) {
        this.mapper = mapper;
        this.environment = environment;
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/secure", true);
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {

        LdapAuthenticationProviderConfigurer<AuthenticationManagerBuilder> localAuth =
                auth
                        .ldapAuthentication()
                        .userDnPatterns(userDnPattern)
                        .groupSearchBase(groupSearchBase)
                        .contextSource()
                        .url(ldapServerUrl)
                        .managerDn(managerDn)
                        .managerPassword(managerPassword)
                        .and()
                        .userDetailsContextMapper(mapper);

        if (!asList(environment.getActiveProfiles()).contains("jumpcloud")) {
            // Jumpcloud doesn't work if passwordCompare() is configured
            localAuth
                    .passwordCompare()
                    .passwordAttribute("userPassword")
                    .passwordEncoder(passwordEncoder());
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

package me.ronygomes.reference.spring_ldap;

import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Service
public class UserExtraInfoContextMapper implements UserDetailsContextMapper {

    @Override
    public UserDetails mapUserFromContext(DirContextOperations ctx, String username,
                                          Collection<? extends GrantedAuthority> authorities) {

        // Custom UserDetails can be loaded after authentication with LDAP
        UserExtraInfo u = new UserExtraInfo(username, "", Collections.emptyList());
        u.setExtraInfo(UUID.randomUUID().toString());

        return u;
    }

    @Override
    public void mapUserToContext(UserDetails user, DirContextAdapter ctx) {
        // LdapUserDetailsManager#copyToContext calls this methods
        // Can be used if changes in UserDetails need to propagate in LDAP
    }
}

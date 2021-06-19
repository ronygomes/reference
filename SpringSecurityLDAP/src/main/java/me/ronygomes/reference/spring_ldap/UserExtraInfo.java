package me.ronygomes.reference.spring_ldap;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserExtraInfo extends User {

    private String extraInfo;

    public UserExtraInfo(String username, String password,
                         Collection<? extends GrantedAuthority> authorities) {

        super(username, password, authorities);
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }
}

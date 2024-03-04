package me.ronygomes.reference.spring_security.dto;

import me.ronygomes.reference.spring_security.domain.Role;
import me.ronygomes.reference.spring_security.domain.User;

import java.io.Serializable;
import java.util.List;

public class RegisterDto implements Serializable {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User toUser(String encodedPassword, Role role) {
        User u = new User();
        u.setEmail(this.getEmail());
        u.setFirstName(this.getFirstName());
        u.setLastName(this.getLastName());
        u.setPassword(encodedPassword);
        u.setRoles(List.of(role));

        return u;
    }
}

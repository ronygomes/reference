package me.ronygomes.reference.oauth2jwt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
public class HomeController {

    @GetMapping("/public")
    public String publicPage() {
        return "Public Page";
    }

    @GetMapping("/private")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public String privatePage(JwtAuthenticationToken token) {
        String roles = token.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .sorted()
                .collect(Collectors.joining(","));

        return String.format("Private Page! Username: %s, Role: %s",
                token.getName(), roles);
    }

    @GetMapping("/admin-only")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String adminOnlyPage() {
        return "Admin Only Page";
    }

}

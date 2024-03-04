package me.ronygomes.reference.spring_security.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtHelper jwtHelper;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtHelper jwtHelper, UserDetailsService userDetailsService) {
        this.jwtHelper = jwtHelper;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = jwtHelper.getToken(request);

        if (Objects.nonNull(token) && jwtHelper.isValidJwtToken(token)) {

            String email = jwtHelper.extractClaim(token, Claims::getSubject);
            UserDetails user = userDetailsService.loadUserByUsername(email);

            if (Objects.nonNull(user) && jwtHelper.isNonExpiredUserToken(token, user)) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}

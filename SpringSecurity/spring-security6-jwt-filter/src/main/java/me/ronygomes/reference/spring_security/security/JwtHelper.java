package me.ronygomes.reference.spring_security.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Component
public class JwtHelper {

    public static final int BEARER_TOKEN_PREFIX_LENGTH = 7;
    private static final String JWT_SECRET = "kLGKjNQ2HbaA828mrVV8pO8wk8FITJ";
    private static final int JWT_EXPIRATION_IN_MILLS = 30 * 60 * 1000;

    public String generateToken(String email, List<String> roles) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", roles)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(Date.from(Instant.now().plus(JWT_EXPIRATION_IN_MILLS, ChronoUnit.MILLIS)))
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                .compact();
    }

    public boolean isNonExpiredUserToken(String token, UserDetails userDetails) {
        try {
            String email = extractClaim(token, Claims::getSubject);
            return email.equals(userDetails.getUsername())
                    && !isTokenExpired(token);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public String getToken(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(BEARER_TOKEN_PREFIX_LENGTH);
        }

        return null;
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public boolean isValidJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return true;
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration)
                .before(new Date());
    }
}

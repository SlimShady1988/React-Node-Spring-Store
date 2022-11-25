package com.store.security;

import com.store.excaptions.JwtAuthenticationException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
@PropertySource("classpath:application.yml")
public class JwtProvider {
    @Value("${jwt.header}")
    private String authorizationHeader;
    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.expiration}")
    private Integer exp;
    private final UserDetailsService userDetailsService;

    public JwtProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }
    public String generateJWT(Long id, String email, String role) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("id", id);
        claims.put("role", role);

        var calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, exp);    //set token life limit to 24 hour
        Date lifeTimeToken = new Date(calendar.getTimeInMillis());

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setExpiration(lifeTimeToken)
                .compact();
    }

    public boolean validateToken (String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException("Json Web Token is expired or invalid", HttpStatus.UNAUTHORIZED);
        }
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken (HttpServletRequest request) {
        return request.getHeader(authorizationHeader);
    }
}

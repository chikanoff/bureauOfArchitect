package com.bureau.auth.jwt;

import com.bureau.auth.util.DateUtil;
import com.bureau.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtService {

    private final JwtProperties jwtProperties;
    private final JwtKeyProvider jwtKeyProvider;
    private final DateUtil dateUtil;

    public String generateToken(UserDetails userDetails) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiryDate = now.plus(jwtProperties.getExpiration());

        String authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setExpiration(dateUtil.toDate(expiryDate))
                .signWith(SignatureAlgorithm.RS256, jwtKeyProvider.getPrivateKey())
                .claim("username", userDetails.getUsername())
                .claim("role", authorities)
                .compact();
    }

    public boolean validateToken(String jwt) {
        try {
            Jwts.parser().setSigningKey(jwtKeyProvider.getPublicKey()).parseClaimsJws(jwt);
            return true;
        } catch (JwtException e) {
            log.warn("Invalid JWT!", e);
        }
        return false;
    }

    public String getUsernameFrom(String jwt) {
        return (String) getClaims(jwt).get("username");
    }

    private Claims getClaims(String jwt) {
        return Jwts.parser()
                .setSigningKey(jwtKeyProvider.getPublicKey())
                .parseClaimsJws(jwt)
                .getBody();
    }
}

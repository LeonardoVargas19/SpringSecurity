package com.cursosapi.springsecurity.services.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Service
public class JwtServices {
    @Value("${security.jwt.expiration-in-minute}")
    private Long EXPIRATION_IN_MINUTE;
    @Value("${security.jwt.secrete.key}")
    private String SECRET_KEY ;

    public String generarToken(UserDetails user, Map<String, Object> extraClins) {
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date((EXPIRATION_IN_MINUTE * 60 * 100) + issuedAt.getTime());

        String jwt = Jwts.builder()
                .header()
                    .type("JWT")
                    .and()
                .signWith(generateKey(),Jwts.SIG.HS256)
                .subject(user.getUsername())
                .issuedAt(issuedAt)
                .expiration(expiration)
                .claims(extraClins)
                .compact();




        return jwt;
    }

    private SecretKey generateKey() {
        byte [] passwordDecoded = Decoders.BASE64.decode(SECRET_KEY);
        System.out.println(new String(passwordDecoded));
        return Keys.hmacShaKeyFor(passwordDecoded);
    }

    public String extractUsername(String jwt) {
        return extractAllClains(jwt).getSubject();
    }

    private Claims extractAllClains(String jwt) {
      return Jwts.parser().verifyWith(generateKey()).build().parseSignedClaims(jwt).getPayload();
    }
}

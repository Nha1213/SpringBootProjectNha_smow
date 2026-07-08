package com.nha.nha_smos.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = " asfasdfhasdfasdfjasdfsfasfjsfsafhgasf245235saklfjas";
    private static final long ACCESS_TOKEN_EXPIRATION = 1000 * 60 *  10; //10min
    private static final long ACCESS_REFRESH_EXPIRATION = 1000 * 60 *  60 * 24 * 7; //7day

//    function return secret_key
    private Key getSingKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
    public String generateAccessJwtToken(String username, List<String> roles, List<String> permission) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("username", username);
        claims.put("roles", roles);
        claims.put("permissions", permission);
        return Jwts.builder()
                .setSubject(username)
                .setClaims(claims)
//              .setClaims(Map.of("roles", roles, "permissions", permission))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION))
                .signWith(getSingKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshJwtToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_REFRESH_EXPIRATION))
                .signWith(getSingKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}

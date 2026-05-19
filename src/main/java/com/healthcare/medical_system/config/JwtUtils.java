package com.healthcare.medical_system.config;

import com.healthcare.medical_system.entity.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {

    @Value("${healthcare.app.jwtSecret}")
    private String jwtSecret;


    @Value("${healthcare.app.jwtExpirationMs}")
    private long expirationTime;




    public String genererToken( String username, Role role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role.name());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();

    }
    public String extractRole(String token){
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .get("role",String.class);
    }



    public   String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenValid(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }


}

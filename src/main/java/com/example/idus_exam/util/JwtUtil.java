package com.example.idus_exam.util;


import com.example.idus_exam.user.Role;
import com.example.idus_exam.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {
    private static final String SECRET = "abcdefghijklmnopqrstuvwxyz0123456789abcdefghijklmnopqrstuvwxyz0123456789";
    private static final int EXP = 30 * 60 * 1000;


    public static User getUser(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return User.builder()
                    .idx(claims.get("userIdx", Long.class))
                    //.username(claims.get("userName", String.class))
                    //.nickname(claims.get("userNickname", String.class))
                    //.phone(claims.get("userPhone", Integer.class))
                    .email(claims.get("userEmail", String.class))
                    //.gender(claims.get("userGender", String.class))
                    .role(Role.valueOf(claims.get("userRole", String.class)))
                    .build();

        } catch (ExpiredJwtException e) {
            System.out.println("토큰이 만료되었습니다!");
            return null;
        }
    }
    public static String generateToken(User user) {
        Claims claims = Jwts.claims();
        claims.put("userIdx", user.getIdx());
        //claims.put("userName", user.getUsername());
        //claims.put("userNickname", user.getNickname());
        //claims.put("userPhone", user.getPhone());
        claims.put("userEmail", user.getEmail());
        //claims.put("userGender", user.getGender());
        claims.put("userRole", user.getRole().name());

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXP))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
        return token;
    }
    public static boolean validate(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            System.out.println("토큰이 만료되었습니다!");
            return false;
        }
        return true;
    }
}

package com.example.demo.service;

import com.example.demo.config.jwt.JwtConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtServ {
    private final JwtConfig jwtConfig;
    private final int ACCESS_TOKEN_EXPIRED = 1000 * 1000 * 60 * 15; // 15 tieng
    private final int REFRESH_TOKEN_EXPIRED = 1000 * 60 * 60 * 24 * 7; // 7 ngày

    public String generateRefreshToken(UserDetails userDetails) {
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return Jwts.builder()
                .setSubject(userDetails.getUsername()) // Lưu tên người dùng
                .claim("roles", roles) // Thêm các vai trò vào JWT claim
                .setIssuedAt(new Date(System.currentTimeMillis())) // Thời gian phát hành token
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRED)) // Thời gian hết hạn
                .signWith(getSignKey(), SignatureAlgorithm.HS256) // Ký JWT
                .compact(); // Tạo và trả về JWT
    }

    public String generateAccessToken(UserDetails userDetails) {
        return Jwts
                .builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRED))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return extractCLaims(token, Claims::getSubject);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpired(token).before(new Date());
    }

    private Date extractExpired(String token) {
        return extractCLaims(token, Claims::getExpiration);
    }

    public <T> T extractCLaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignKey() {
        byte[] heyByte = Decoders.BASE64.decode(jwtConfig.getSecretKey());
        return Keys.hmacShaKeyFor(heyByte);
    }
}

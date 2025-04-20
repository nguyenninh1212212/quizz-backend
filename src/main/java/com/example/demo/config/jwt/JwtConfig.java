package com.example.demo.config.jwt;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class JwtConfig {
    private String secretKey;

    public JwtConfig(@Value("${spring.security.oauth2.resourceserver.jwt.secret-key}") String secretKey) {
        this.secretKey = secretKey;
    }

    public String getSecretKey() {
        return secretKey;
    }
}

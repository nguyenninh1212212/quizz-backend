package com.example.demo.config.jwt;

import lombok.Setter;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class JwtConfig {
    private String secretKey;

    public JwtConfig(@Value("${spring.security.oauth2.resourceserver.jwt.secret-key}") String secretKey) {
        this.secretKey = secretKey;
    }

    public String getSecretKey() {
        return secretKey;
    }
}

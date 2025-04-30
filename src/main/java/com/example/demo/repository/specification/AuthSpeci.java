package com.example.demo.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.model.entity.Auth;

public class AuthSpeci {

    public static Specification<Auth> hasUsername(String username) {
        return (root, query, cb) -> {
            if (username != null) {
                return cb.equal(root.get("username"), username);
            }
            return cb.conjunction();
        };
    }

    public static Specification<Auth> hasEmail(String email) {
        return (root, query, cb) -> {
            if (email != null) {
                return cb.equal(root.get("email"), email);
            }
            return cb.conjunction();
        };
    }

}

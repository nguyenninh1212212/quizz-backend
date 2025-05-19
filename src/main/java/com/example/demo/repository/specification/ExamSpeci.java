package com.example.demo.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.model.entity.Auth;
import com.example.demo.model.entity.Exam;

public class ExamSpeci {
    public static Specification<Exam> hasAuth(Auth id) {
        return (root, query, cb) -> {
            if (id != null) {
                return cb.equal(root.get("auth"), id);
            }
            return null;
        };
    }
}

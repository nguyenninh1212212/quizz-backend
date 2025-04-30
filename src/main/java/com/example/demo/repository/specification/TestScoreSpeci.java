package com.example.demo.repository.specification;

import com.example.demo.model.entity.Auth;
import com.example.demo.model.entity.TestScore;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class TestScoreSpeci {

    public static Specification<TestScore> hasSchool(UUID school) {
        return (root, query, cb) -> cb.equal(root.get("school_id"), school);
    }

    public static Specification<TestScore> hasSubject(UUID subject) {
        return (root, query, cb) -> cb.equal(root.get("subject_id"), subject);
    }

    public static Specification<TestScore> hasAuth(Auth auth) {
        return (root, query, cb) -> cb.equal(root.get("auth"), auth);
    }

    public static Specification<TestScore> scoreGreaterThanOrEqual(Double score) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("score"), score);
    }

    public static Specification<TestScore> scoreLessThanOrEqual(Double score) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("score"), score);
    }
}
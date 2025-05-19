package com.example.demo.repository.specification;

import com.example.demo.model.entity.School;

import org.springframework.data.jpa.domain.Specification;

public class SchoolSpeci {

    public static Specification<School> hasId(String id) {
        return (root, query, cb) -> {
            if (id != null) {
                return cb.equal(root.get("id"), id);
            }
            return cb.conjunction();
        };
    }
}
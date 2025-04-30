package com.example.demo.repository.specification;

import com.example.demo.model.entity.School;

import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

public class SchoolSpeci {

    public static Specification<School> hasId(UUID id) {
        return (root, query, cb) -> {
            if (id != null) {
                return cb.equal(root.get("id"), id);
            }
            return cb.conjunction();
        };
    }
}
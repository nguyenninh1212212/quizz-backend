package com.example.demo.repository.specification;

import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.model.entity.Auth;
import com.example.demo.model.entity.Folder;
import com.example.demo.model.entity.Save;

public class FolderSpeci {
    public static Specification<Folder> hasAuth(Auth id) {
        return (root, query, cb) -> {
            if (id != null) {
                return cb.equal(root.get("auth"), id);
            }
            return null;
        };
    }

    public static Specification<Folder> hasId(UUID id) {
        return (root, query, cb) -> {
            if (id != null) {
                return cb.equal(root.get("id"), id);
            }
            return null;
        };
    }

}

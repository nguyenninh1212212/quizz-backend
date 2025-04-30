package com.example.demo.repository.specification;

import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.model.entity.Auth;
import com.example.demo.model.entity.Exam;
import com.example.demo.model.entity.Folder;
import com.example.demo.model.entity.Save;

public class SaveSpeci {
    public static Specification<Save> hasAuth(Auth id) {
        return (root, query, cb) -> {
            if (id != null) {
                return cb.equal(root.get("auth"), id);
            }
            return null;
        };
    }

    public static Specification<Save> hasFolder(Folder folderId) {
        return (root, query, cb) -> {
            return cb.equal(root.get("folder"), folderId);
        };
    }

    public static Specification<Save> hasExam(Exam id) {
        return (root, query, cb) -> {
            if (id != null) {
                return cb.equal(root.get("exam"), id);
            }
            return null;
        };
    }

}

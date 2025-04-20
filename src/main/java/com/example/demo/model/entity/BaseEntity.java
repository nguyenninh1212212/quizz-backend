package com.example.demo.model.entity;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PreUpdate;
import lombok.Data;

import java.time.Instant;

@MappedSuperclass
@Data
public class BaseEntity extends Base {
    private Instant updateAt;
    private Instant deleteAt;
    @PreUpdate
    public void preUpdate() {
        updateAt = Instant.now();
    }
}

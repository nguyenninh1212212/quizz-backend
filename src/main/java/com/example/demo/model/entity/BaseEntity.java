package com.example.demo.model.entity;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PreUpdate;
import lombok.Setter;
import lombok.Getter;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity extends Base {
    private Instant updateAt;
    private Instant deleteAt;

    @PreUpdate
    public void preUpdate() {
        updateAt = Instant.now();
    }
}

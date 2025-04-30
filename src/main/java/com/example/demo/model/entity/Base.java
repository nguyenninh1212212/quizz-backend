package com.example.demo.model.entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@MappedSuperclass
@Data
public class Base {
    @Id
    @UuidGenerator
    private UUID id;
    private Instant createdAt = Instant.now();

}

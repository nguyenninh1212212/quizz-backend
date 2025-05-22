package com.example.demo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Setter;
import lombok.Getter;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
@Getter
@Setter
public class Base {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(updatable = false, nullable = false)
    private String id;
    private Instant createdAt = Instant.now();

}

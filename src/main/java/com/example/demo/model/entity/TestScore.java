package com.example.demo.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TestScore extends Base {
    private Double score;
    @ManyToOne
    private School school;
    @ManyToOne

    private Subject subject;
    @ManyToOne

    private Auth auth;
}

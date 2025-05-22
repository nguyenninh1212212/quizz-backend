package com.example.demo.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TestScore extends Base {
    private Double score;
    @ManyToOne
    private School school;
    @ManyToOne

    private Subject subject;
    @ManyToOne

    private Auth auth;
}

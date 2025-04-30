package com.example.demo.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Exam extends Base {
    private String docs;
    private String cover;
    private String title;
    @ManyToOne
    private School school;
    @ManyToOne
    private Subject subject;
    @ManyToOne
    private Auth auth;

}

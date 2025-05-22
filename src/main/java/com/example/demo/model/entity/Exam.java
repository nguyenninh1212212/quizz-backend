package com.example.demo.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;
import lombok.Getter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
    @ManyToOne
    private Level level;

}

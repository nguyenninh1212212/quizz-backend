package com.example.demo.model.dto.Res.Exam;

import java.time.Instant;

import lombok.Setter;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ExamResDTO {
    private String id;
    private String title;
    private String cover;
    private String school;
    private String subject;
    private String auth;
    private Instant createdAt;
    private String avatar;

}

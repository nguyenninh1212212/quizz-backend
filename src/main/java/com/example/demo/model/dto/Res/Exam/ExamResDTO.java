package com.example.demo.model.dto.Res.Exam;

import java.time.Instant;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
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

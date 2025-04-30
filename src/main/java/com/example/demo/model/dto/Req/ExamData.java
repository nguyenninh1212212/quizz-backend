package com.example.demo.model.dto.Req;

import java.util.UUID;

import lombok.Data;

@Data
public class ExamData {
    private String title;
    private UUID subject;
    private UUID school;
}

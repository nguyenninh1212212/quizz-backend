package com.example.demo.model.dto.Res;

import java.time.Instant;

import lombok.Data;

@Data
public class TestScoreResDTO {
    private Double score;
    private String exam;
    private Instant createdAt;
}

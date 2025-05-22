package com.example.demo.model.dto.Res;

import java.time.Instant;

import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class TestScoreResDTO {
    private Double score;
    private String exam;
    private Instant createdAt;
}

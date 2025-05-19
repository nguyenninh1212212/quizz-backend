package com.example.demo.model.dto.Res.Exam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LevelResDTO {
    private String id;
    private String name;
    private String id_subject;
}

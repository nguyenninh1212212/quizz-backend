package com.example.demo.model.dto.Res.Exam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LevelResDTO {
    private String id;
    private String name;
    private String id_subject;
}

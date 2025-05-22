package com.example.demo.model.dto.Res.Exam;

import java.util.List;

import lombok.Setter;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ExamResDetailDTO extends ExamResDTO {
    private List<QuestionDTO> quest;
}

package com.example.demo.model.dto.Res.Exam;

import java.util.List;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ExamResDetailDTO extends ExamResDTO {
    private List<QuestionDTO> quest;
}

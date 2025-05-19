package com.example.demo.model.dto.Res.Exam;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {
    private String question;
    private List<String> answer;
    private List<String> correct;
}

package com.example.demo.model.dto.Res.Exam;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {
    private String question;
    private List<String> answer;
    private List<String> correct;
}

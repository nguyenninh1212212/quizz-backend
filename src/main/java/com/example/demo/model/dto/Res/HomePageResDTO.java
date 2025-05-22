package com.example.demo.model.dto.Res;

import java.util.List;

import com.example.demo.model.dto.Res.Exam.ExamResDTO;

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
public class HomePageResDTO {
    private List<ExamResDTO> exams;
    private List<ExamResDTO> myList;
}

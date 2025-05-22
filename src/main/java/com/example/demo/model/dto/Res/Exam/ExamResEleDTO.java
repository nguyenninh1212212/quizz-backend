package com.example.demo.model.dto.Res.Exam;

import java.util.List;

import com.example.demo.model.dto.Res.SchoolResDTO;
import com.example.demo.model.dto.Res.SubjectResDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExamResEleDTO {
    private List<SchoolResDTO> schools;
    private List<SubjectResDTO> subjects;
    private List<LevelResDTO> level;
}

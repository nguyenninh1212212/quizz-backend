package com.example.demo.model.dto.Res.Folder;

import java.util.List;

import com.example.demo.model.dto.Res.Exam.ExamResDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FolderResDTO {
    private String id;
    private String name;
    private List<ExamResDTO> exams;
    private Long examCount;
}

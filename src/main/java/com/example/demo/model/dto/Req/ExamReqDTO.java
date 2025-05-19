package com.example.demo.model.dto.Req;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ExamReqDTO {
    private MultipartFile cover;
    private MultipartFile docx;
    private ExamData examData;

}

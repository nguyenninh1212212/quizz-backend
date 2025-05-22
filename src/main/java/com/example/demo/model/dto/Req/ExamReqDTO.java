package com.example.demo.model.dto.Req;

import org.springframework.web.multipart.MultipartFile;

import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class ExamReqDTO {
    private MultipartFile cover;
    private MultipartFile docx;
    private ExamData examData;

}

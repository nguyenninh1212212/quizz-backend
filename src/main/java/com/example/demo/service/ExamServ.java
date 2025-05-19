package com.example.demo.service;

import com.example.demo.model.dto.Req.ExamData;
import com.example.demo.model.dto.Req.ExamReqDTO;
import com.example.demo.model.dto.Res.HomePageResDTO;
import com.example.demo.model.dto.Res.PageRes;
import com.example.demo.model.dto.Res.ResponseData;
import com.example.demo.model.dto.Res.Exam.ExamResDTO;
import com.example.demo.model.dto.Res.Exam.ExamResDetailDTO;
import com.example.demo.model.dto.Res.Exam.ExamResEleDTO;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ExamServ {
    void create(MultipartFile cover, MultipartFile docx, ExamData dto);

    PageRes<List<ExamResDTO>> getAll(Pageable pageable);

    ExamResDetailDTO getById(String id);

    ExamResEleDTO getCreateExamElement();

    HomePageResDTO getHome();
}

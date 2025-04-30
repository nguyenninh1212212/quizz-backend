package com.example.demo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.exceptions.Status;
import com.example.demo.model.dto.Req.ExamReqDTO;
import com.example.demo.model.dto.Res.HomePageResDTO;
import com.example.demo.model.dto.Res.PageRes;
import com.example.demo.model.dto.Res.ResponseData;
import com.example.demo.model.dto.Res.Exam.ExamResDTO;
import com.example.demo.model.dto.Res.Exam.ExamResDetailDTO;
import com.example.demo.model.dto.Res.Exam.ExamResEleDTO;
import com.example.demo.service.ExamServ;
import com.example.demo.utils.ResponseUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/exams")
@RequiredArgsConstructor
public class ExamController {
    private final ExamServ examServ;

    @PostMapping("/cre")
    public ResponseEntity<ResponseData<String>> createExam(
            @ModelAttribute  ExamReqDTO req) {

        examServ.create(req.getCover(), req.getDocx(), req.getExamData());
        return ResponseUtil.ok("Success");

    }

    @GetMapping("/ele")
    public ResponseEntity<ResponseData<ExamResEleDTO>> getCreateExamElement() {
        return ResponseUtil.ok(examServ.getCreateExamElement());

    }

    @GetMapping
    public ResponseEntity<ResponseData<PageRes<List<ExamResDTO>>>> getAllExams(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        PageRes<List<ExamResDTO>> exams = examServ.getAll(pageable);
        return ResponseUtil.ok(exams);
    }

    @GetMapping("/home")
    public ResponseEntity<ResponseData<HomePageResDTO>> getHomePage() {

        HomePageResDTO home = examServ.getHome();
        return ResponseUtil.ok(home);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<ExamResDetailDTO>> getExamById(@PathVariable UUID id) {
        ExamResDetailDTO examDetail = examServ.getById(id);
        return ResponseUtil.ok(examDetail);
    }
}
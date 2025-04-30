package com.example.demo.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.exceptions.ExceptionHandle;
import com.example.demo.exceptions.Status;
import com.example.demo.model.dto.Req.ExamData;
import com.example.demo.model.dto.Req.File.ImageReqDTO;
import com.example.demo.model.dto.Req.File.RawReqDTO;
import com.example.demo.model.dto.Res.HomePageResDTO;
import com.example.demo.model.dto.Res.PageRes;
import com.example.demo.model.dto.Res.SchoolResDTO;
import com.example.demo.model.dto.Res.SubjectResDTO;
import com.example.demo.model.dto.Res.Exam.ExamResDTO;
import com.example.demo.model.dto.Res.Exam.ExamResDetailDTO;
import com.example.demo.model.dto.Res.Exam.ExamResEleDTO;
import com.example.demo.model.dto.Res.Exam.QuestionDTO;
import com.example.demo.model.entity.Auth;
import com.example.demo.model.entity.Exam;
import com.example.demo.model.entity.School;
import com.example.demo.model.entity.Subject;
import com.example.demo.repository.ExamRepo;
import com.example.demo.repository.SchoolRepo;
import com.example.demo.repository.SubjectRepo;
import com.example.demo.repository.specification.ExamSpeci;
import com.example.demo.service.CloundServ;
import com.example.demo.service.ExamServ;
import com.example.demo.utils.AuthContext;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExamServImpl implements ExamServ {
    private final ExamRepo examrepo;
    private final AuthContext authContext;
    private final CloundServ cloundServ;
    private final SchoolRepo schoolRepo;
    private final SubjectRepo subjectRepo;
    private static String URL_COVER = "https://tribecapac.org/wp-content/uploads/2024/06/noman-website-300x300.jpg";

    @Override
    @Transactional

    public void create(MultipartFile cover, MultipartFile docx, ExamData req) {
        Auth auth = authContext.auth();
        if (docx == null) {
            throw new ExceptionHandle(Status.NO_CONTENT, "file docx found");
        }
        if (req == null) {
            throw new ExceptionHandle(Status.INVALID_INPUT, "json");
        }

        try (XWPFDocument document = new XWPFDocument(docx.getInputStream())) {
            String question = null;
            List<String> answers = new ArrayList<>();
            List<String> correct = new ArrayList<>();

            for (XWPFParagraph para : document.getParagraphs()) {
                String line = para.getText().trim();
                if (line.isEmpty()) {
                    continue;
                }

                // Kiểm tra định dạng "Câu" để xác định câu hỏi
                if (line.toLowerCase().startsWith("câu")) {
                    if (question != null) {
                        // Nếu câu hỏi trước đó đã có, reset lại câu trả lời và câu đúng
                        answers.clear();
                        correct.clear();
                    }
                    question = line;
                } else {
                    // Kiểm tra các câu trả lời bắt đầu với dấu "*"
                    if (line.startsWith("*")) {
                        String cleanAnswer = line.length() > 1 ? line.substring(1).trim() : "";
                        answers.add(cleanAnswer);
                        correct.add(cleanAnswer); // Câu trả lời đúng
                    } else {
                        answers.add(line); // Câu trả lời thông thường
                    }
                }
            }

            // Kiểm tra sau khi xử lý tài liệu docx
            if (question == null || answers.isEmpty()) {
                throw new ExceptionHandle(Status.INVALID_INPUT, "No questions or answers found in the docx file");
            }

        } catch (IOException e) {
            throw new ExceptionHandle(Status.INVALID_INPUT, "Error reading docx file");
        }

        // Xử lý việc upload file cover và docx
        RawReqDTO raw = RawReqDTO.builder()
                .file(docx)
                .build();
        String imageUrl;
        String docxUrl = cloundServ.uploadFile(raw);
        if (cover != null) {
            ImageReqDTO image = ImageReqDTO.builder()
                    .file(cover)
                    .build();
            imageUrl = cloundServ.uploadFile(image);
        } else {
            imageUrl = URL_COVER;
        }

        School school = schoolRepo.findById(req.getSchool())
                .orElseThrow(() -> new ExceptionHandle(Status.INVALID_INPUT, "School"));
        Subject subject = subjectRepo.findById(req.getSubject())
                .orElseThrow(() -> new ExceptionHandle(Status.INVALID_INPUT, "Subject"));

        Exam exam = Exam
                .builder()
                .auth(auth)
                .docs(docxUrl)
                .cover(imageUrl)
                .school(school)
                .subject(subject)
                .title(req.getTitle())
                .build();
        examrepo.save(exam);
    }

    @Override
    public PageRes<List<ExamResDTO>> getAll(Pageable pageable) {
        Page<Exam> examList = examrepo.findAll(pageable);
        List<ExamResDTO> res = examList.getContent().stream().map(exam -> {
            ExamResDTO examResDTO = ExamResDTO
                    .builder()
                    .id(exam.getId())
                    .cover(exam.getCover())
                    .title(exam.getTitle())
                    .school(exam.getSchool().getName())
                    .auth(exam.getAuth().getFullname())
                    .createdAt(exam.getCreatedAt())
                    .avatar(exam.getAuth().getAvatar())
                    .subject(exam.getSubject().getName())
                    .build();
            return examResDTO;
        }).collect(Collectors.toList());

        return PageRes.<List<ExamResDTO>>builder()
                .data(res)
                .limit(pageable.getPageSize())
                .page(pageable.getPageNumber())
                .totalPage(examList.getTotalPages())
                .build();
    }

    @Override
    public ExamResDetailDTO getById(UUID id) {
        Exam exam = examrepo.findById(id).orElseThrow(() -> new ExceptionHandle(Status.INVALID_INPUT, "exam"));
        List<QuestionDTO> questionList = new ArrayList<>();
        URL url;
        try {
            url = new java.net.URI(exam.getDocs()).toURL();
        } catch (Exception e) {
            throw new ExceptionHandle(Status.INVALID_INPUT, " URL in exam docs");
        }

        try (InputStream inputStream = url.openStream();
                XWPFDocument document = new XWPFDocument(inputStream)) {

            String question = null;
            List<String> answers = new ArrayList<>();
            List<String> correct = new ArrayList<>();

            for (XWPFParagraph para : document.getParagraphs()) {
                String line = para.getText().trim();
                if (line.isEmpty())
                    continue;

                if (line.toLowerCase().startsWith("câu")) {
                    if (question != null) {
                        questionList.add(new QuestionDTO(question, new ArrayList<>(answers), new ArrayList<>(correct)));
                        answers.clear();
                        correct.clear();
                    }
                    question = line;
                } else {
                    if (line.startsWith("*")) {
                        String cleanAnswer = line.length() > 1 ? line.substring(1).trim() : "";
                        answers.add(cleanAnswer);
                        correct.add(cleanAnswer); // If correct answers are the same as regular answers
                    } else {
                        answers.add(line); // Regular answer
                    }
                }
            }

            // Add last question
            if (question != null) {
                questionList.add(new QuestionDTO(question, new ArrayList<>(answers), new ArrayList<>(correct)));
            }

        } catch (IOException e) {
            throw new ExceptionHandle(Status.INVALID_INPUT, "File");
        }

        return ExamResDetailDTO
                .builder()
                .quest(questionList)
                .auth(exam.getAuth().getFullname())
                .cover(exam.getCover())
                .createdAt(exam.getCreatedAt())
                .school(exam.getSchool().getName())
                .subject(exam.getSubject().getName())
                .title(exam.getTitle())
                .avatar(exam.getAuth().getAvatar())
                .id(exam.getId())
                .build();
    }

    @Override
    public ExamResEleDTO getCreateExamElement() {
        List<School> schoolList = schoolRepo.findAll();
        List<Subject> subjectList = subjectRepo.findAll();
        return ExamResEleDTO.builder()
                .schools(schoolList.stream().map(s -> SchoolResDTO.builder()
                        .id(s.getId())
                        .name(s.getName())
                        .build()).collect(Collectors.toList()))
                .subjects(subjectList.stream().map(s -> SubjectResDTO.builder()
                        .id(s.getId())
                        .name(s.getName())
                        .build()).collect(Collectors.toList()))
                .build();
    }

    @Override
    public HomePageResDTO getHome() {
        Pageable pageable = Pageable.ofSize(5);
        Auth auth = authContext.auth();
        Specification<Exam> spec = Specification.where(ExamSpeci.hasAuth(auth));
        Page<Exam> examList = examrepo.findAll(pageable);
        Page<Exam> myExamList = examrepo.findAll(spec, pageable);
        List<ExamResDTO> res = examList.getContent().stream().map(exam -> {
            ExamResDTO examResDTO = ExamResDTO
                    .builder()
                    .id(exam.getId())
                    .cover(exam.getCover())
                    .title(exam.getTitle())
                    .school(exam.getSchool().getName())
                    .auth(exam.getAuth().getFullname() != null ? exam.getAuth().getFullname() : "anonymous")
                    .createdAt(exam.getCreatedAt())
                    .subject(exam.getSubject().getName())
                    .avatar(exam.getAuth().getAvatar())
                    .build();
            return examResDTO;
        }).collect(Collectors.toList());
        List<ExamResDTO> resMyList = myExamList.getContent().stream().map(exam -> {
            ExamResDTO examResDTO = ExamResDTO
                    .builder()
                    .id(exam.getId())
                    .cover(exam.getCover())
                    .title(exam.getTitle())
                    .school(exam.getSchool().getName())
                    .createdAt(exam.getCreatedAt())
                    .subject(exam.getSubject().getName())
                    .avatar(exam.getAuth().getAvatar())

                    .build();
            return examResDTO;
        }).collect(Collectors.toList());

        return HomePageResDTO
                .builder()
                .myList(resMyList)
                .exams(res)
                .build();
    }
}

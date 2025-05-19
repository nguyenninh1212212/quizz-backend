package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.ExceptionHandle;
import com.example.demo.exceptions.Status;
import com.example.demo.model.dto.Res.PageRes;
import com.example.demo.model.dto.Res.Exam.ExamResDTO;
import com.example.demo.model.dto.Res.Folder.FolderResDTO;
import com.example.demo.model.entity.Auth;
import com.example.demo.model.entity.Exam;
import com.example.demo.model.entity.Folder;
import com.example.demo.model.entity.Save;
import com.example.demo.repository.ExamRepo;
import com.example.demo.repository.FolderRepo;
import com.example.demo.repository.SaveRepo;
import com.example.demo.repository.specification.FolderSpeci;
import com.example.demo.repository.specification.SaveSpeci;
import com.example.demo.service.FolderServ;
import com.example.demo.utils.AuthContext;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FolderServImpl implements FolderServ {
        private final AuthContext authContext;
        private final FolderRepo folderRepo;
        private final SaveRepo saveRepo;
        private final ExamRepo examRepo;

        @Override
        public PageRes<List<FolderResDTO>> getAllFolder(Pageable pageable) {
                Auth auth = authContext.auth();
                Specification spec = Specification.where(
                                FolderSpeci.hasAuth(auth));
                Page<Folder> folderList = folderRepo.findAll(spec, pageable);
                List<FolderResDTO> fResDTOs = folderList.getContent().stream().map(folder -> FolderResDTO
                                .builder()
                                .examCount(saveRepo.count(SaveSpeci.hasFolder(folder)))
                                .name(folder.getName())
                                .id(folder.getId())
                                .build()).collect(Collectors.toList());
                return PageRes.<List<FolderResDTO>>builder()
                                .data(fResDTOs)
                                .limit(folderList.getSize())
                                .page(folderList.getNumber())
                                .totalPage(folderList.getTotalPages())
                                .build();
        }

        @Override
        public void createFolder(String name) {
                Auth auth = authContext.auth();
                Folder folder = Folder
                                .builder()
                                .auth(auth)
                                .name(name)
                                .build();
                folderRepo.save(folder);
        }

        @Override
        @Transactional
        public void deleteFolder(String id) {
                Auth auth = authContext.auth(); // Lấy thông tin người dùng đang đăng nhập

                Specification<Folder> spec = Specification.where(
                                FolderSpeci.hasAuth(auth))
                                .and(FolderSpeci.hasId(id));

                Folder folder = folderRepo.findOne(spec)
                                .orElseThrow(() -> new ExceptionHandle(Status.INVALID_INPUT, "Folder"));

                folderRepo.delete(folder); // Xoá folder khỏi CSDL
        }

        @Override
        public FolderResDTO FolderDetail(String id) {
                Auth auth = authContext.auth();

                Specification<Folder> spec = Specification.where(
                                FolderSpeci.hasAuth(auth))
                                .and(FolderSpeci.hasId(id));

                Folder folder = folderRepo.findOne(spec)
                                .orElseThrow(() -> new ExceptionHandle(Status.INVALID_INPUT, "Folder"));
                List<ExamResDTO> exams = saveRepo.findAll(SaveSpeci.hasFolder(folder)).stream()
                                .map(save -> ExamResDTO.builder()
                                                .auth(save.getAuth().getFullname())
                                                .cover(save.getExam().getCover())
                                                .id(save.getExam().getId())
                                                .school(save.getExam().getSchool().getName())
                                                .subject(save.getExam().getSubject().getName())
                                                .title(save.getExam().getTitle())
                                                .createdAt(save.getExam().getCreatedAt())
                                                .build())
                                .collect(Collectors.toList());
                FolderResDTO folderResDTO = FolderResDTO.builder()
                                .id(folder.getId())
                                .name(folder.getName())
                                .examCount(saveRepo.count(SaveSpeci.hasFolder(folder)))
                                .exams(exams)
                                .build();
                return folderResDTO;
        }

        @Override
        @Transactional
        public void addToFolder(String folderId, String examId) {
                Auth auth = authContext.auth();
                Exam exam = examRepo.findById(examId)
                                .orElseThrow(() -> new ExceptionHandle(Status.INVALID_INPUT, "Exam not found"));
                Folder folder = folderRepo.findById(folderId)
                                .orElseThrow(() -> new ExceptionHandle(Status.INVALID_INPUT, "Folder not found"));

                Specification<Save> spec = Specification.where(
                                SaveSpeci.hasAuth(auth))
                                .and(SaveSpeci.hasExam(exam))
                                .and(SaveSpeci.hasFolder(folder));
                Save exist = saveRepo.findOne(spec).orElse(null);

                if (exist != null) {
                        throw new ExceptionHandle(Status.CONFLICT, "Exam already exists in the folder");
                }

                Save save = Save.builder()
                                .folder(folder)
                                .exam(exam)
                                .auth(auth)
                                .build();
                saveRepo.save(save);
        }

}

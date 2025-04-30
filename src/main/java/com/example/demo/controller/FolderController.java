package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.Res.PageRes;
import com.example.demo.model.dto.Res.ResponseData;
import com.example.demo.model.dto.Res.Folder.FolderResDTO;
import com.example.demo.service.FolderServ;
import com.example.demo.utils.ResponseUtil;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/folder")
@RequiredArgsConstructor
public class FolderController {
    private final FolderServ folderServ;

    @GetMapping("/all")
    public ResponseEntity<ResponseData<PageRes<List<FolderResDTO>>>> getAllFolders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        PageRes<List<FolderResDTO>> result = folderServ.getAllFolder(pageable);
        return ResponseUtil.ok(result);
    }

    @PostMapping("/cre")
    public ResponseEntity<?> createFolder(@RequestParam String name) {
        folderServ.createFolder(name);
        return ResponseUtil.cre();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFolder(@PathVariable UUID id) {
        folderServ.deleteFolder(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<FolderResDTO>> getFolderDetail(@PathVariable UUID id) {
        FolderResDTO folderDetail = folderServ.FolderDetail(id);
        return ResponseUtil.ok(folderDetail);
    }

    @PostMapping("/{folderId}/add")
    public ResponseEntity<?> addToFolder(@PathVariable UUID folderId, @RequestParam UUID examId) {
        folderServ.addToFolder(folderId, examId);
        return ResponseUtil.cre();

    }

}

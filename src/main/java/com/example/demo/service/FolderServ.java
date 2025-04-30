package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.example.demo.model.dto.Res.PageRes;
import com.example.demo.model.dto.Res.Folder.FolderResDTO;

public interface FolderServ {

    PageRes<List<FolderResDTO>> getAllFolder(Pageable pageable);

    void createFolder(String name);

    void deleteFolder(UUID id);

    void addToFolder(UUID folderId, UUID examId);

    FolderResDTO FolderDetail(UUID id);

}
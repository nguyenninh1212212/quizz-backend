package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.demo.model.dto.Res.PageRes;
import com.example.demo.model.dto.Res.Folder.FolderResDTO;

public interface FolderServ {

    PageRes<List<FolderResDTO>> getAllFolder(Pageable pageable);

    void createFolder(String name);

    void deleteFolder(String id);

    void addToFolder(String folderId, String examId);

    FolderResDTO FolderDetail(String id);

}
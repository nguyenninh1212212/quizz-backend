package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.model.entity.Folder;

public interface FolderRepo extends JpaRepository<Folder, String>, JpaSpecificationExecutor<Folder> {
}
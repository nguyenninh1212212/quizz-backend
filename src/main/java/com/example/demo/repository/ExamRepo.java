package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Exam;

@Repository
public interface ExamRepo extends JpaRepository<Exam, String>, JpaSpecificationExecutor<Exam> {
}
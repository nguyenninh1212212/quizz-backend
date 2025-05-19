package com.example.demo.repository;

import com.example.demo.model.entity.TestScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TestScoreRepo extends JpaRepository<TestScore, String>, JpaSpecificationExecutor<TestScore> {
    // Thêm custom query nếu cần
}
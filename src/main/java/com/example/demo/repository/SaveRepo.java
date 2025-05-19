package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.model.entity.Save;

public interface SaveRepo extends JpaRepository<Save, String>, JpaSpecificationExecutor<Save> {

}

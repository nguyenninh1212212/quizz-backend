package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.model.entity.School;

public interface SchoolRepo extends JpaRepository<School, String>, JpaSpecificationExecutor<School> {

}

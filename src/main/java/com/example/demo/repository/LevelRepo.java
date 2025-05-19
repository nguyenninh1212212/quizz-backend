package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.entity.Level;

public interface LevelRepo extends JpaRepository<Level, String> {

}

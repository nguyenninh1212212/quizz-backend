package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.model.dto.Req.LevelReqDTO;
import com.example.demo.model.entity.Level;
import com.example.demo.repository.LevelRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LevelServImpl {
    private final LevelRepo levelRepo;

    public void create(LevelReqDTO req) {
        Level level = Level.builder().subject_id(req.getSubject_id()).name(req.getName()).build();
        levelRepo.save(level);
    }
}

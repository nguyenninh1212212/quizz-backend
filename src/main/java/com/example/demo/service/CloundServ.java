package com.example.demo.service;

import java.io.IOException;

import com.example.demo.model.dto.Req.File.FileBase;

public interface CloundServ {
    String uploadFile(FileBase file);
}

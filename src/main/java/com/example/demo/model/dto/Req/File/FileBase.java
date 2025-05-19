package com.example.demo.model.dto.Req.File;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder

public abstract class FileBase {
    private MultipartFile file;

    public abstract String getFileType();
}

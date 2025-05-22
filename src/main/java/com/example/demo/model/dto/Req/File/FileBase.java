package com.example.demo.model.dto.Req.File;

import org.springframework.web.multipart.MultipartFile;

import lombok.Setter;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public abstract class FileBase {
    private MultipartFile file;

    public abstract String getFileType();
}

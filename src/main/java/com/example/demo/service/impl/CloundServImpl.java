package com.example.demo.service.impl;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.demo.model.dto.Req.File.FileBase;
import com.example.demo.service.CloundServ;
import com.example.demo.utils.FileUtil;
import com.example.demo.exceptions.ExceptionHandle;
import com.example.demo.exceptions.Status;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CloundServImpl implements CloundServ {
    private final Cloudinary cloudinary;

    @Override
    public String uploadFile(FileBase file) {
        // Kiểm tra kích thước file và loại file
        FileUtil.isFileSizeValid(file.getFile());
        FileUtil.isFileTypeValid(file.getFile());

        Map uploadResult;
        try {
            uploadResult = cloudinary.uploader().upload(file.getFile().getBytes(),
                    ObjectUtils.asMap("resource_type", file.getFileType().toLowerCase()));

            return uploadResult.get("secure_url").toString();
        } catch (IOException e) {
            throw new ExceptionHandle(Status.INTERNAL_ERROR, "File upload failed: " + e.getMessage());
        }
    }
}

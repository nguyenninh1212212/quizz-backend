package com.example.demo.utils;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.exceptions.ExceptionHandle;
import com.example.demo.exceptions.Status;

import lombok.experimental.UtilityClass;

@UtilityClass
public class FileUtil {
    private static final long MAX_FILE_SIZE = 100 * 1024 * 1024;
    private static final List<String> ALLOWED_CONTENT_TYPES = List.of(
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document", // .docx
            "image/jpeg", "image/png", "image/jpg");

    public static void isFileSizeValid(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new ExceptionHandle(Status.NO_CONTENT, "file");

        }
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new ExceptionHandle(Status.FILE_EXPIRED);
        }
    }

    public static void isFileTypeValid(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new ExceptionHandle(Status.FILE_EXPIRED, "file");
        }

        String contentType = file.getContentType();
        if (contentType == null || !ALLOWED_CONTENT_TYPES.contains(contentType)) {
            throw new ExceptionHandle(Status.INVALID_INPUT, "Only file .docx or png,jpg,jpeg");
        }
    }

}

package com.example.demo.utils;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.Res.ResponseData;

public class ResponseUtil {

    public static <T> ResponseEntity<ResponseData<T>> ok(T data, String status) {
        return ResponseEntity.badRequest().body(
                ResponseData.<T>builder()
                        .data(data)
                        .status(status)
                        .build());
    }

}

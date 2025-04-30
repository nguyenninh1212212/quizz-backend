package com.example.demo.utils;

import java.util.Map;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.exceptions.ExceptionHandle;
import com.example.demo.model.dto.Res.ResponseData;
import com.example.demo.model.dto.Res.ResponseError;

public class ResponseUtil {

        public static <T> ResponseEntity<ResponseData<T>> ok(T data) {
                return ResponseEntity.ok(
                                ResponseData.<T>builder()
                                                .data(data)
                                                .status(200)
                                                .build());
        }

        public static <T> ResponseEntity<ResponseError> ex(ExceptionHandle ex) {
                return ResponseEntity
                                .status(ex.getStatus().getHttpStatus())
                                .body(ResponseError.builder()
                                                .status(ex.getStatus().getHttpStatus().value())
                                                .message(ex.getMessage())
                                                .build());
        }

        public static <T> ResponseEntity<ResponseError> cre() {
                return ResponseEntity
                                .status(HttpStatus.SC_CREATED)
                                .body(ResponseError.builder()
                                                .status(HttpStatus.SC_CREATED)
                                                .message("Success")
                                                .build());
        }

}

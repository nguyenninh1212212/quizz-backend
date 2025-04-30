package com.example.demo.exceptions;

import org.apache.coyote.BadRequestException;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.model.dto.Res.ResponseError;
import com.example.demo.utils.ResponseUtil;

@RestControllerAdvice
public class GlobalException {
        @ExceptionHandler(ExceptionHandle.class)
        public ResponseEntity<?> handleException(ExceptionHandle ex) {
                return ResponseUtil.ex(ex);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<?> handleGeneralException(Exception ex) {
                ResponseError errorResponse = ResponseError.builder()
                                .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                                .message(ex.getMessage())
                                .build();

                return ResponseEntity
                                .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                                .body(errorResponse);
        }

        @ExceptionHandler(BadRequestException.class)
        public ResponseEntity<?> handleBadRequestException(BadRequestException ex) {
                // Tạo thông tin lỗi cho BadRequestException
                ResponseError errorResponse = ResponseError.builder()
                                .status(HttpStatus.SC_BAD_REQUEST)
                                .message(ex.getMessage()) // Truyền thông điệp lỗi từ BadRequestException
                                .build();

                return ResponseEntity
                                .status(HttpStatus.SC_BAD_REQUEST) // Trả về mã lỗi 400
                                .body(errorResponse); // Trả về ResponseError với thông tin lỗi
        }

        @ExceptionHandler(MissingServletRequestParameterException.class)
        public ResponseEntity<?> handleMissingParameter(MissingServletRequestParameterException ex) {
                ResponseError errorResponse = ResponseError.builder()
                                .status(HttpStatus.SC_BAD_REQUEST)
                                .message("Missing parameter: " + ex.getParameterName())
                                .build();

                return ResponseEntity
                                .status(HttpStatus.SC_BAD_REQUEST)
                                .body(errorResponse);
        }

        @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
        public ResponseEntity<ResponseError> handleUnsupportedMediaTypeException(
                        HttpMediaTypeNotSupportedException ex) {
                ResponseError errorResponse = ResponseError.builder()
                                .status(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE)
                                .message(ex.getMessage())
                                .build();

                return ResponseEntity.status(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE).body(errorResponse);
        }
}

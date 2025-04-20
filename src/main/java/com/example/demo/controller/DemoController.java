package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Res.ResponseData;
import com.example.demo.utils.ResponseUtil;

@RestController
@RequestMapping("/demo")
public class DemoController {
    @GetMapping
    public ResponseEntity<ResponseData<String>> getDemo() {
        return ResponseUtil.ok("", "success");
    }

}

package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.Req.AuthenticateReqDTO;
import com.example.demo.model.dto.Res.AuthenticationResponse;
import com.example.demo.model.dto.Res.ResponseData;
import com.example.demo.service.AuthServ;
import com.example.demo.utils.ResponseUtil;
import com.example.demo.validator.LoginValidateGroup;
import com.example.demo.validator.RegisterValidateGroup;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthServ authServ;

    @PostMapping("/register")
    public ResponseEntity<ResponseData<AuthenticationResponse>> register(
            @RequestBody @Validated(RegisterValidateGroup.class) AuthenticateReqDTO req) {
        return ResponseUtil.ok(authServ.register(req));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseData<AuthenticationResponse>> login(
            @RequestBody @Validated(LoginValidateGroup.class) AuthenticateReqDTO req) {
        return ResponseUtil.ok(authServ.authenticate(req));
    }

    @GetMapping("/profile")
    public ResponseEntity<ResponseData<String>> profile() {
        return ResponseUtil.ok(authServ.profile());
    }

}

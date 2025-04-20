package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Req.AuthenticateReqDTO;
import com.example.demo.dto.Req.RegisterReqDTO;
import com.example.demo.dto.Res.AuthenticationResponse;
import com.example.demo.dto.Res.ResponseData;
import com.example.demo.service.AuthServ;
import com.example.demo.utils.ResponseUtil;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthServ authServ;

    @PostMapping("/register")
    public ResponseEntity<ResponseData<AuthenticationResponse>> register(@RequestBody @Validated RegisterReqDTO req) {
        return ResponseUtil.ok(authServ.register(req), "success");
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseData<AuthenticationResponse>> login(@RequestBody AuthenticateReqDTO req) {
        return ResponseUtil.ok(authServ.authenticcate(req), "success");

    }

}

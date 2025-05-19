package com.example.demo.service;

import com.example.demo.model.dto.Req.AuthenticateReqDTO;
import com.example.demo.model.dto.Res.AuthenticationResponse;

public interface AuthServ {

    AuthenticationResponse register(AuthenticateReqDTO req);

    AuthenticationResponse authenticate(AuthenticateReqDTO req);

    String profile();
}

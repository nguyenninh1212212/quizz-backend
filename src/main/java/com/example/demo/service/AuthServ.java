package com.example.demo.service;

import com.example.demo.model.dto.Req.AuthenticateReqDTO;
import com.example.demo.model.dto.Req.RegisterReqDTO;
import com.example.demo.model.dto.Res.AuthenticationResponse;

public interface AuthServ {

    AuthenticationResponse register(RegisterReqDTO req);

    AuthenticationResponse authenticate(AuthenticateReqDTO req);
}

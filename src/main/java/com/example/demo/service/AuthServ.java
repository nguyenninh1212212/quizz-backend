package com.example.demo.service;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Req.AuthenticateReqDTO;
import com.example.demo.dto.Req.RegisterReqDTO;
import com.example.demo.dto.Res.AuthenticationResponse;
import com.example.demo.model.entity.Auth;
import com.example.demo.model.entity.Role;
import com.example.demo.model.enums.ROLE;
import com.example.demo.repository.AuthRepo;
import com.example.demo.repository.specification.AuthSpeci;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServ {
    private final AuthRepo authRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtServ jwtServ;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterReqDTO req) {
        Specification<Auth> spec = Specification.where(null);
        spec = spec.and(AuthSpeci.hasUsername(req.getUsername()));
        spec = spec.and(AuthSpeci.hasEmail(req.getEmail()));
        if (!authRepo.findAll(spec).isEmpty()) {
            throw new RuntimeException("user or email already");
        }
        Auth auth = Auth
                .builder()
                .email(req.getEmail())
                .fullname(req.getFullname())
                .username(req.getUsername())
                .password(passwordEncoder.encode(req.getPassword()))
                .role(Role.builder().name(ROLE.CUSTOMER).build())
                .build();
        authRepo.save(auth);
        String accessToken = jwtServ.generateAccessToken(auth);
        String refreshToken = jwtServ.generateRefreshToken(auth);
        return AuthenticationResponse
                .builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse authenticcate(AuthenticateReqDTO req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        Auth auth = authRepo.findByUsername(req.getUsername()).orElseThrow(() -> new RuntimeException("a"));
        String accessToken = jwtServ.generateAccessToken(auth);
        return AuthenticationResponse
                .builder()
                .accessToken(accessToken)
                .refreshToken("")
                .build();

    }
}

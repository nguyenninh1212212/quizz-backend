package com.example.demo.service.impl;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.ExceptionHandle;
import com.example.demo.exceptions.Status;
import com.example.demo.model.dto.Req.AuthenticateReqDTO;
import com.example.demo.model.dto.Res.AuthenticationResponse;
import com.example.demo.model.entity.Auth;
import com.example.demo.model.entity.Role;
import com.example.demo.model.enums.ROLE;
import com.example.demo.repository.AuthRepo;
import com.example.demo.repository.specification.AuthSpeci;
import com.example.demo.service.AuthServ;
import com.example.demo.service.JwtServ;
import com.example.demo.utils.AuthContext;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServImpl implements AuthServ {
        private final AuthRepo authRepo;
        private final PasswordEncoder passwordEncoder;
        private final JwtServ jwtServ;
        private final AuthenticationManager authenticationManager;
        private final AuthContext authContext;

        public AuthenticationResponse register(AuthenticateReqDTO req) {
                Specification<Auth> spec = Specification
                                .where(AuthSpeci.hasEmail(req.getEmail()))
                                .or(AuthSpeci.hasUsername(req.getUsername()));
                if (authRepo.findOne(spec).isPresent()) {
                        throw new ExceptionHandle(Status.ALREADY_EXISTS, "Username or email");
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

        @Override
        public AuthenticationResponse authenticate(AuthenticateReqDTO req) {
                try {
                        authenticationManager.authenticate(
                                        new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
                } catch (AuthenticationException ex) {
                        throw new ExceptionHandle(Status.VALIDATION_FAILED, "Incorrect username or password");
                }
                Specification<Auth> spec = Specification
                                .where(AuthSpeci.hasUsername(req.getUsername()));

                Auth auth = authRepo.findOne(spec).get();

                // Tạo token
                String accessToken = jwtServ.generateAccessToken(auth);

                return AuthenticationResponse.builder()
                                .accessToken(accessToken)
                                .refreshToken("")
                                .build();
        }

        @Override
        public String profile() {
                return authContext.auth().getFullname();
        }

}

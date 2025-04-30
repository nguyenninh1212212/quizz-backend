package com.example.demo.model.dto.Req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticateReqDTO {
    @NonNull
    private String username;

    @NonNull
    private String password;
}

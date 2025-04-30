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
public class RegisterReqDTO {
    @NonNull
    private String fullname;

    @NonNull
    private Integer age;

    @NonNull
    private String email;

    @NonNull
    private String phoneNumber;

    @NonNull
    private String username;

    @NonNull
    private String password;

}

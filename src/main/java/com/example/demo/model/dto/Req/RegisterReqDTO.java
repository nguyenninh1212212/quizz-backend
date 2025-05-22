package com.example.demo.model.dto.Req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@Setter
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

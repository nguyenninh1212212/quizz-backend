package com.example.demo.model.dto.Req;

import com.example.demo.validator.LoginValidateGroup;
import com.example.demo.validator.RegisterValidateGroup;

import jakarta.validation.constraints.NotBlank;
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
public class AuthenticateReqDTO {

    @NonNull
    @NotBlank(message = "Age cannot be blank", groups = { LoginValidateGroup.class, RegisterValidateGroup.class })
    private Integer age;

    @NotBlank(message = "Username cannot be blank", groups = { LoginValidateGroup.class, RegisterValidateGroup.class })
    private String username;

    @NotBlank(message = "Password cannot be blank", groups = { LoginValidateGroup.class, RegisterValidateGroup.class })
    private String password;

    @NotBlank(message = "Fullname cannot be blank", groups = RegisterValidateGroup.class)
    private String fullname;

    @NotBlank(message = "Email cannot be blank", groups = RegisterValidateGroup.class)
    private String email;

    @NotBlank(message = "Phone number cannot be blank", groups = RegisterValidateGroup.class)
    private String phoneNumber;
}

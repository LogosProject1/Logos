package com.logos.auth.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class SignUpDto {
    @Length(min=1,max=30)
    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @Length(min=8,max=20)
    @NotBlank
    private String password;

    @NotBlank
    private String phone;
}

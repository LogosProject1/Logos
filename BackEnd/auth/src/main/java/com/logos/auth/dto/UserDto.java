package com.logos.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
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

    @NotBlank
    private String type;

}

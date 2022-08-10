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
public class UserUpdateDto {
    @Length(min=1,max=30)
    @NotBlank
    private String name;

    @Length(min=8,max=20)
    @NotBlank
    private String originPassword;

    @Length(min=8,max=20)
    @NotBlank
    private String newPassword;

    @NotBlank
    private String phone;
}

package com.logos.payment.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class VerifyDto {
    @NotBlank
    String amount;

    @NotBlank
    String merchant_uid;

    @NotBlank
    String result;
}

package com.logos.payment.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class OrderDto {
    @NotBlank
    String amount;

    @NotBlank
    String paymentType;
}

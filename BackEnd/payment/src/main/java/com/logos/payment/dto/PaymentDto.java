package com.logos.payment.dto;

import com.logos.payment.domain.ResultType;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Data
@Builder
public class PaymentDto {
    private String id;

    private Long amount;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private ResultType result;

    private String paymentType;
}

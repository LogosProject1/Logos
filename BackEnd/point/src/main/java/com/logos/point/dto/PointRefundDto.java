package com.logos.point.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PointRefundDto {
    private boolean result;
    private String message;
}

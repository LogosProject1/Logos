package com.logos.point.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class PointDto {
    private String id;

    private Long amount;

    private Long remain;

    private LocalDateTime modifyTime;

    private String type;
}

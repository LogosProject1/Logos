package com.logos.point.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PointHistoryDto {
    private String id;

    private String userEmail;

    private Long amount;

    private LocalDateTime modifyTime;

    private String type;
}
package com.logos.rating.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RatingResultDto {
    private int rate;
    private String userEmail;
    private String content;
    private LocalDateTime modifiedAt;
}

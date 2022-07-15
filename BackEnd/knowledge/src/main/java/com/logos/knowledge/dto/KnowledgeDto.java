package com.logos.knowledge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KnowledgeDto {
    private String title;

    private String category;

    private String content;

    private String price;

    private String startTime;

    private String endTime;
}

package com.logos.knowledge.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class KnowledgeDto {
    private String title;

    private String category;

    private String content;

    private String price;

    private String startTime;

    private String endTime;
}

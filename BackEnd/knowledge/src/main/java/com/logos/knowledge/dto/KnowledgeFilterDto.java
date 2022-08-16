package com.logos.knowledge.dto;

import lombok.Data;

@Data
public class KnowledgeFilterDto {
    private String category;

    private String title;

    private String startTime;

    private String endTime;

    private String minPrice;

    private String maxPrice;
}

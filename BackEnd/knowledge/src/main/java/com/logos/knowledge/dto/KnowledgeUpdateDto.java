package com.logos.knowledge.dto;

import lombok.Data;

@Data
public class KnowledgeUpdateDto {
    private String knowledgeId;

    private String title;

    private String category;

    private String content;

    private String price;

    private String startTime;

    private String endTime;
}

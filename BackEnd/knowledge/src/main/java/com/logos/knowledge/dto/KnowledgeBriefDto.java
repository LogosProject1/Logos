package com.logos.knowledge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KnowledgeBriefDto {
    private String title;

    private String price;

    private String startTime;

    private String endTime;

    private String updated_at;
}

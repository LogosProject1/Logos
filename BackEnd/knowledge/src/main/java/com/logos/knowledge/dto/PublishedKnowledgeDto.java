package com.logos.knowledge.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PublishedKnowledgeDto {
    private List<KnowledgeBriefDto> publishList;
    private int totalPage;
}

package com.logos.knowledge.dto;

import com.logos.knowledge.domain.Knowledge;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SubscribedKnowledgeDto {
    private List<KnowledgeBriefDto> subscribeList;
    private int totalPage;
}

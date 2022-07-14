package com.logos.knowledge.service;

import com.logos.knowledge.domain.Knowledge;
import com.logos.knowledge.dto.KnowledgeDto;

public interface KnowledgeService {
    Knowledge create(KnowledgeDto knowledge);
}

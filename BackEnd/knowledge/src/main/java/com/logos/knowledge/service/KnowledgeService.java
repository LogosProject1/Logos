package com.logos.knowledge.service;

import com.logos.knowledge.domain.Knowledge;
import com.logos.knowledge.dto.KnowledgeBriefDto;
import com.logos.knowledge.dto.KnowledgeDto;
import com.logos.knowledge.dto.KnowledgeFilterDto;
import com.logos.knowledge.dto.KnowledgeUpdateDto;

import java.util.List;

public interface KnowledgeService {
    Knowledge create(String email, KnowledgeDto knowledge);

    boolean delete(String email, String knowledgeId);

    Knowledge update(String email, KnowledgeUpdateDto knowledge);

    List<KnowledgeBriefDto> search(String keyword);

    KnowledgeDto read(String knowledgeId);

    List<KnowledgeBriefDto> filter(KnowledgeFilterDto knowledgeFilterDto);
}

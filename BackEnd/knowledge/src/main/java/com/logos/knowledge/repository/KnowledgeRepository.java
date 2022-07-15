package com.logos.knowledge.repository;

import com.logos.knowledge.domain.Knowledge;
import com.logos.knowledge.dto.KnowledgeBriefDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KnowledgeRepository extends JpaRepository<Knowledge,String> {
    List<KnowledgeBriefDto> findByTitleContaining(String keyword);
}

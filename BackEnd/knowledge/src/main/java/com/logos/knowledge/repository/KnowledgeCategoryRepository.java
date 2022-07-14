package com.logos.knowledge.repository;

import com.logos.knowledge.domain.KnowledgeCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KnowledgeCategoryRepository extends JpaRepository<KnowledgeCategory,Long> {
}

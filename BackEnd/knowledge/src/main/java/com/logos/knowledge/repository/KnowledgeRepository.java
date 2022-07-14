package com.logos.knowledge.repository;

import com.logos.knowledge.domain.Knowledge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KnowledgeRepository extends JpaRepository<Knowledge,Long> {
}

package com.logos.rating.repository;

import com.logos.rating.domain.Knowledge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KnowledgeRepository extends JpaRepository<Knowledge,String> {
}

package com.logos.point.repository;

import com.logos.point.domain.Knowledge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KnowledgeRepository extends JpaRepository<Knowledge,String> {
}

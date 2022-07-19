package com.logos.session.repository;

import com.logos.session.domain.Knowledge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KnowledgeRepository extends JpaRepository<Knowledge,String> {
}

package com.logos.knowledge.repository;

import com.logos.knowledge.domain.Knowledge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KnowledgeRepository extends JpaRepository<Knowledge,String> {
    List<Knowledge> findByTitleContains(String keyword);
}

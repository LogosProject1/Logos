package com.logos.rating.repository;

import com.logos.rating.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating,String> {
    Rating findByUserEmailAndKnowledgeId(String email, String knowledgeId);

    List<Rating> findByKnowledgeIdOrderByModifiedAt(String knowledgeId);
}

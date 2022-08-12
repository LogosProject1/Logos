package com.logos.rating.repository;

import com.logos.rating.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating,String> {
    Rating findByUserEmailAndKnowledgeId(String email, String knowledgeId);

    List<Rating> findByKnowledgeOrderByModifiedAt(String knowledgeId);

    List<Rating> findByKnowledgeIdAndRate(String knowledgeId, int rate);

    List<Rating> findByRate(int rate);

    List<Rating> findByUserEmail(String userEmail);

    List<Rating> findByUserEmailAndRate(String userEmail, int rate);

    List<Rating> findByUserEmailAndKnowledgeIdAndRate(String userEmail, String knowledgeId, int rate);
}

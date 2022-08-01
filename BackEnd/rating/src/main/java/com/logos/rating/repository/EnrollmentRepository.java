package com.logos.rating.repository;

import com.logos.rating.domain.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment,String> {
    Enrollment findByUserEmailAndKnowledgeId(String email, String knowledgeId);
}

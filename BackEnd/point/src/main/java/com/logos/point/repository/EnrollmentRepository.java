package com.logos.point.repository;

import com.logos.point.domain.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment,String> {
    Enrollment findByUserEmailAndKnowledgeId(String email, String knowledgeId);
}

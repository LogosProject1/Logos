package com.logos.knowledge.repository;

import com.logos.knowledge.domain.Enrollment;
import com.logos.knowledge.domain.Knowledge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment,String> {
    @Query(value = "SELECT distinct e FROM Enrollment e " +
            "join fetch e.knowledge " +
            "WHERE e.userEmail = :email "+
            "ORDER BY e.modified_at DESC",
            countQuery = "SELECT count(e) from Enrollment e WHERE e.userEmail = :email")
    Page<Enrollment> findSubscribedUsingFetchJoin(@Param("email")String email, Pageable pageable);

    List<Enrollment> findByKnowledge(Knowledge knowledge);
}

package com.logos.knowledge.repository;

import com.logos.knowledge.domain.Knowledge;
import com.logos.knowledge.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface KnowledgeRepository extends JpaRepository<Knowledge,String> {
    List<Knowledge> findByTitleContains(String keyword);

    Long deleteByWriter(User user);

    @Query("select k from Knowledge k "+
           "where k.category.id like :category and k.startTime >= :startTime and k.endTime <= :endTime "+
           "and k.price between :minPrice and :maxPrice and k.title like :title")
    List<Knowledge> findByFilter(@Param("category") String category,
                                 @Param("startTime") LocalDateTime startTime,
                                 @Param("endTime") LocalDateTime endTime,
                                 @Param("minPrice") Long minPrice,
                                 @Param("maxPrice") Long maxPrice,
                                 @Param("title") String title);

    Page<Knowledge> findByWriter(User byEmail, Pageable pageable);
}

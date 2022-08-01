package com.logos.rating.repository;

import com.logos.rating.domain.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointHistoryRepository extends JpaRepository<PointHistory,String> {
    List<PointHistory> findByUserEmailOrderByModifiedAtDesc(String email);
}

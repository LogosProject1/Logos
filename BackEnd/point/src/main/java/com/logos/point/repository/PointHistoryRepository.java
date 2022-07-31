package com.logos.point.repository;

import com.logos.point.domain.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointHistoryRepository extends JpaRepository<PointHistory,String> {
    List<PointHistory> findByUserEmailOrderByModifiedAtDesc(String email);
}

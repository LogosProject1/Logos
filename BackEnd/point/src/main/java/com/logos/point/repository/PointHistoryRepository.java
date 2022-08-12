package com.logos.point.repository;

import com.logos.point.domain.PointHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointHistoryRepository extends JpaRepository<PointHistory,String> {
    Page<PointHistory> findByUserEmailOrderByModifiedAtDesc(String email, Pageable pageable);
}

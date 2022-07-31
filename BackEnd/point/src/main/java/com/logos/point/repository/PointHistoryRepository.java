package com.logos.point.repository;

import com.logos.point.domain.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointHistoryRepository extends JpaRepository<PointHistory,String> {
}

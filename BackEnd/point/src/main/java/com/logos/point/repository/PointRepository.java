package com.logos.point.repository;

import com.logos.point.domain.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointRepository extends JpaRepository<PointHistory, String> {
    List<PointHistory> findByUserEmail(String email);
}

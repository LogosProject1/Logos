package com.logos.payment.repository;

import com.logos.payment.domain.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointHistoryRepository extends JpaRepository<PointHistory,String> {
}

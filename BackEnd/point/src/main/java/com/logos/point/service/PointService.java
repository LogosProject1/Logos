package com.logos.point.service;

import com.logos.point.domain.PointHistory;
import com.logos.point.dto.PointHistoryDto;
import com.logos.point.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PointService {
    private final PointRepository pointRepository;

    public List<PointHistoryDto> getPointHistory(String email) {
        List<PointHistory> byUserEmail = pointRepository.findByUserEmail(email);
        List<PointHistoryDto> result = new ArrayList<>();

        for(PointHistory point : byUserEmail){
            result.add(PointHistoryDto.builder()
                    .id(point.getId())
                    .amount(point.getAmount())
                    .modifyTime(point.getModifyTime())
                    .type(point.getType())
                    .build());
        }
        return result;
    }
}

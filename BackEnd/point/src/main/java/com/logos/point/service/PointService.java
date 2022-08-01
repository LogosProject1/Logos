package com.logos.point.service;

import com.logos.point.domain.*;
import com.logos.point.dto.PointHistoryDto;
import com.logos.point.repository.EnrollmentRepository;
import com.logos.point.repository.KnowledgeRepository;
import com.logos.point.repository.PointHistoryRepository;
import com.logos.point.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PointService {
    private final PointHistoryRepository pointHistoryRepository;
    private final KnowledgeRepository knowledgeRepository;
    private final UserRepository userRepository;
    private final EnrollmentRepository enrollmentRepository;

    public List<PointHistoryDto> getPointHistory(String email) {
        List<PointHistory> byUserEmail = pointHistoryRepository.findByUserEmailOrderByModifiedAtDesc(email);
        List<PointHistoryDto> result = new ArrayList<>();

        for(PointHistory pointHistory : byUserEmail){
            result.add(PointHistoryDto.builder()
                    .id(pointHistory.getId())
                    .amount(pointHistory.getAmount())
                    .remain(pointHistory.getRemain())
                    .modifyTime(pointHistory.getModifiedAt())
                    .type(pointHistory.getType().toString())
                    .build());
        }
        return result;
    }

    @Transactional
    public boolean purchaseKnowledge(String email, String knowledgeId) {
        User byEmail = userRepository.findByEmail(email);
        if(byEmail == null){
            log.error("존재하지 않는 유저입니다.");
            return false;
        }

        Optional<Knowledge> byId = knowledgeRepository.findById(knowledgeId);
        if(byId.isEmpty()){
            log.error("존재하지 않는 지식입니다.");
            return false;
        }
        Knowledge knowledge = byId.get();
        // 유저의 포인트가 충분한지 확인
        if(byEmail.getPoint() < knowledge.getPrice()){
            return false;
        }
        // enrollment 레코드
        Enrollment enrollment = Enrollment.createEnrollment(knowledgeId, email, knowledge.getPrice());

        // 유저 포인트 감소
        byEmail.pointDecrease(knowledge.getPrice());

        // 포인트 히스토리 생성
        PointHistory pointHistory = PointHistory.createPointHistory(email, knowledge.getPrice(), byEmail.getPoint(), PointHistoryType.DESC);

        enrollmentRepository.save(enrollment);
        userRepository.save(byEmail);
        pointHistoryRepository.save(pointHistory);

        return true;
    }

    @Transactional
    public boolean refundKnowledge(String email, String knowledgeId) {
        Enrollment enrollment = enrollmentRepository.findByUserEmailAndKnowledgeId(email, knowledgeId);

        if(enrollment == null){
            return false;
        }

        enrollmentRepository.delete(enrollment);

        return true;
    }
}

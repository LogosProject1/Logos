package com.logos.point.service;

import com.logos.point.domain.*;
import com.logos.point.dto.PointDto;
import com.logos.point.dto.PointHistoryDto;
import com.logos.point.dto.PointRefundDto;
import com.logos.point.repository.EnrollmentRepository;
import com.logos.point.repository.KnowledgeRepository;
import com.logos.point.repository.PointHistoryRepository;
import com.logos.point.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PointService {
    private final PointHistoryRepository pointHistoryRepository;
    private final KnowledgeRepository knowledgeRepository;
    private final UserRepository userRepository;
    private final EnrollmentRepository enrollmentRepository;

    public PointHistoryDto getPointHistory(String email, Pageable pageable) {
        Page<PointHistory> byUserEmail = pointHistoryRepository.findByUserEmailOrderByModifiedAtDesc(email,pageable);
        List<PointDto> result = new ArrayList<>();

        for(PointHistory pointHistory : byUserEmail.getContent()){
            result.add(PointDto.builder()
                    .id(pointHistory.getId())
                    .amount(pointHistory.getAmount())
                    .remain(pointHistory.getRemain())
                    .modifyTime(pointHistory.getModifiedAt())
                    .type(pointHistory.getType().toString())
                    .build());
        }

        return PointHistoryDto.builder()
                .pointHistory(result)
                .totalPage(byUserEmail.getTotalPages())
                .build();
    }

    @Transactional
    public Map<String,String> purchaseKnowledge(String email, String knowledgeId) {
        User byEmail = userRepository.findByEmail(email);
        HashMap<String, String> result = new HashMap<>();
        if(byEmail == null){
            log.error("존재하지 않는 유저입니다.");
            result.put("message","존재하지 않는 유저입니다.");
            result.put("result","FALSE");
            return result;
        }


        Optional<Knowledge> byId = knowledgeRepository.findById(knowledgeId);
        if(byId.isEmpty()){
            log.error("존재하지 않는 지식입니다.");
            result.put("message","존재하지 않는 지식입니다.");
            result.put("result","FALSE");
            return result;
        }

        Enrollment byUserEmailAndKnowledgeId = enrollmentRepository.findByUserEmailAndKnowledgeId(email, knowledgeId);
        if(byUserEmailAndKnowledgeId != null){
            log.error("이미 구매한 지식입니다.");
            result.put("message","이미 구매한 지식입니다.");
            result.put("result","FALSE");
            return result;
        }

        if(byEmail.getEmail().equals(byId.get().getWriter().getEmail())){
            log.error("회원님이 생성한 지식입니다.");
            result.put("message","회원님이 생성한 지식입니다.");
            result.put("result","FALSE");
            return result;
        }


        if(LocalDateTime.now().isAfter(byId.get().getStartTime())){
            log.error("이미 세션을 시작한 지식입니다.");
            result.put("message","이미 세션을 시작한 지식입니다.");
            result.put("result","FALSE");
            return result;
        }

        Knowledge knowledge = byId.get();
        // 유저의 포인트가 충분한지 확인
        if(byEmail.getPoint() < knowledge.getPrice()){
            result.put("message","등록 중 문제가 발생하였습니다.");
            result.put("result","FALSE");
            return result;
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

        result.put("message","정상적으로 등록되었습니다.");
        result.put("result","TRUE");
        return result;
    }

    @Transactional
    public PointRefundDto refundKnowledge(String email, String knowledgeId) {
        Enrollment enrollment = enrollmentRepository.findByUserEmailAndKnowledgeId(email, knowledgeId);
        User byEmail = userRepository.findByEmail(email);

        if(byEmail == null){
            log.error("존재하지 않는 유저입니다.");
            return PointRefundDto.builder().result(false).message("존재하지 않는 유저입니다.").build();
        }

        if(enrollment == null){
            return PointRefundDto.builder().result(false).message("신청하지 않은 지식입니다.").build();
        }

        Optional<Knowledge> byId = knowledgeRepository.findById(enrollment.getKnowledgeId());

        if(byId.isEmpty()){
            log.error("존재하지 않는 지식입니다.");
            return PointRefundDto.builder().result(false).message("존재하지 않는 지식입니다.").build();
        }
        //시작시간 이후이면 환불 불가
        if(LocalDateTime.now().isAfter(byId.get().getStartTime())){
            log.error("이미 세션을 시작한 지식입니다.");
            return PointRefundDto.builder().result(false).message("이미 세션을 시작한 지식입니다.").build();
        }

        // 유저 포인트 증가
        byEmail.pointIncrease(enrollment.getPurchasePrice());

        // 포인트 히스토리 생성
        PointHistory pointHistory = PointHistory.createPointHistory(email, enrollment.getPurchasePrice(), byEmail.getPoint(), PointHistoryType.INC);

        enrollmentRepository.delete(enrollment);
        userRepository.save(byEmail);
        pointHistoryRepository.save(pointHistory);

        return PointRefundDto.builder().result(true).message("정상적으로 환불되었습니다.").build();
    }
}

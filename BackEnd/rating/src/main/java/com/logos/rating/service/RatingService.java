package com.logos.rating.service;

import com.logos.rating.domain.*;
import com.logos.rating.dto.RatingDto;
import com.logos.rating.dto.RatingFilterDto;
import com.logos.rating.dto.RatingResultDto;
import com.logos.rating.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RatingService {
    private final KnowledgeRepository knowledgeRepository;
    private final RatingRepository ratingRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final PointHistoryRepository pointHistoryRepository;

    @Transactional
    public RatingResultDto ratingKnowledge(String email, String knowledgeId, RatingDto ratingDto) {
        Optional<Knowledge> byId = knowledgeRepository.findById(knowledgeId);
        //해당 지식이 있는지 확인
        if(byId.isEmpty()){
            return null;
        }

        //해당 지식을 구입했었는지 확인(추후 세션 토큰을 통해 세션도 끝났는지 확인해봐도 됨)
        Enrollment enrollment = enrollmentRepository.findByUserEmailAndKnowledgeId(email, knowledgeId);
        if(enrollment == null){
            return null;
        }

        Rating rating = ratingRepository.findByUserEmailAndKnowledgeId(email, knowledgeId);

        //이전 평가가 있으면 갱신
        if(rating == null){
            rating = Rating.createRating(knowledgeId, email, ratingDto.getRate(), ratingDto.getContent());
            ratingRepository.save(rating);
        }
        else{
            rating.setRate(ratingDto.getRate());
            rating.setContent(ratingDto.getContent());
            rating.setModifiedAt(LocalDateTime.now());
        }

        //평가 완료 후 rate에 따라 환불
        Long purchasePrice = enrollment.getPurchasePrice();
        double refundRate = 1 - (double)rating.getRate()/100;
        Long refundPrice = (long)(purchasePrice * refundRate);

        User user = userRepository.findByEmail(email);
        user.pointIncrease(refundPrice);
        PointHistory refundHistory = PointHistory.createPointHistory(user.getEmail(), refundPrice, user.getPoint(), PointHistoryType.INC);
        pointHistoryRepository.save(refundHistory);

        //평가 완료 후 지식 공유자한테 포인트 주기
        Knowledge knowledge = byId.get();
        User writer = knowledge.getWriter();
        Long amount = purchasePrice - refundPrice;
        writer.pointIncrease(amount);
        PointHistory earnHistory = PointHistory.createPointHistory(writer.getEmail(), amount, writer.getPoint(), PointHistoryType.INC);
        pointHistoryRepository.save(earnHistory);

        //평가 완료 후 enrollment에서 유저 삭제
        enrollmentRepository.delete(enrollment);

        return RatingResultDto.builder()
                .rate(rating.getRate())
                .content(rating.getContent())
                .userEmail(rating.getUserEmail())
                .modifiedAt(rating.getModifiedAt())
                .build();
    }

    public List<RatingResultDto> getKnowledgeRating(String knowledgeId) {
        List<Rating> byKnowledgeId = ratingRepository.findByKnowledgeOrderByModifiedAt(knowledgeId);
        List<RatingResultDto> ratingResultDtoList = new ArrayList<>();

        makeRatingResultList(ratingResultDtoList, byKnowledgeId);
        return ratingResultDtoList;
    }

    public List<RatingResultDto> getFilteredRating(RatingFilterDto ratingFilterDto) {
        List<RatingResultDto> ratingResultDtoList = new ArrayList<>();

        if (ratingFilterDto.getUserEmail() == null) {
            if (ratingFilterDto.getRate() == null) {
                if (ratingFilterDto.getKnowledgeId() == null) {
                    // 모두 null
                } else {
                    // knowledgeId에 해당하는 모든 리턴
                    List<Rating> byKnowledgeId = ratingRepository.findByKnowledgeOrderByModifiedAt(ratingFilterDto.getKnowledgeId());
                    makeRatingResultList(ratingResultDtoList, byKnowledgeId);
                }
            } else {
                List<Rating> byRate;
                if (ratingFilterDto.getKnowledgeId() == null) {
                    // 평점 필터링, 전체 지식에 대한
                    byRate = ratingRepository.findByRate(Integer.parseInt(ratingFilterDto.getRate()));

                } else {
                    // 해당 지식에 대한 평점 필터링
                    byRate = ratingRepository.findByKnowledgeIdAndRate(ratingFilterDto.getKnowledgeId(),
                            Integer.parseInt(ratingFilterDto.getRate()));

                }
                makeRatingResultList(ratingResultDtoList, byRate);
            }
        }
        else {
            // userEmail이 들어가있다
            if (ratingFilterDto.getRate() == null) {
                if (ratingFilterDto.getKnowledgeId() == null) {
                    // 해당 유저의 전체 평점
                    List<Rating> byEmail = ratingRepository.findByUserEmail(ratingFilterDto.getUserEmail());

                    makeRatingResultList(ratingResultDtoList, byEmail);
                } else {
                    // 해당 유저의 해당하는 지식의 평점
                    Rating byEmail = ratingRepository.findByUserEmailAndKnowledgeId(ratingFilterDto.getUserEmail(),
                            ratingFilterDto.getKnowledgeId());

                    makeRatingResultList(ratingResultDtoList, Arrays.asList(byEmail));
                }
            } else {
                // 목표 점수가 들어가있다
                if (ratingFilterDto.getKnowledgeId() == null) {
                    // 해당 유저의 목표 점수에 해당하는 모든 평점
                    List<Rating> byEmail = ratingRepository.findByUserEmailAndRate(ratingFilterDto.getUserEmail(),
                            Integer.parseInt(ratingFilterDto.getRate()));

                    makeRatingResultList(ratingResultDtoList, byEmail);
                } else {
                    // 해당 유저의 해당 지식의 목표 점수에 해당하는 평점
                    List<Rating> byEmailKnowledgeRate = ratingRepository.findByUserEmailAndKnowledgeIdAndRate(
                            ratingFilterDto.getUserEmail(),
                            ratingFilterDto.getKnowledgeId(),
                            Integer.parseInt(ratingFilterDto.getRate()));

                    makeRatingResultList(ratingResultDtoList, byEmailKnowledgeRate);
                }
            }
        }

        return ratingResultDtoList;
    }

    private void makeRatingResultList(List<RatingResultDto> ratingResultDtoList, List<Rating> ratingList) {
        for (Rating rating : ratingList) {
            ratingResultDtoList.add(
                    RatingResultDto.builder()
                            .rate(rating.getRate())
                            .userEmail(rating.getUserEmail())
                            .content(rating.getContent())
                            .modifiedAt(rating.getModifiedAt())
                            .build()
            );
        }
    }
}

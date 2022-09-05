package com.logos.knowledge.service;

import com.logos.knowledge.domain.Category;
import com.logos.knowledge.domain.Enrollment;
import com.logos.knowledge.domain.Knowledge;
import com.logos.knowledge.domain.User;
import com.logos.knowledge.dto.*;
import com.logos.knowledge.repository.CategoryRepository;
import com.logos.knowledge.repository.EnrollmentRepository;
import com.logos.knowledge.repository.KnowledgeRepository;
import com.logos.knowledge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class KnowledgeService{
    private final CategoryRepository categoryRepository;

    private final KnowledgeRepository knowledgeRepository;

    private final UserRepository userRepository;

    private final EnrollmentRepository enrollmentRepository;

    @Transactional
    public Knowledge create(String email, KnowledgeDto knowledge) {
        User writer = userRepository.findByEmail(email);

        return createKnowledge(writer,knowledge);
    }

    @Transactional
    public boolean delete(String email, String knowledgeId) {
        Optional<Knowledge> knowledge = knowledgeRepository.findById(knowledgeId);

        if(checkWriter(email, knowledge)){
            //지식 삭제
            knowledgeRepository.deleteById(knowledgeId);

            return true;
        }
        else{
            return false;
        }
    }
    public List<KnowledgeBriefDto> filter(KnowledgeFilterDto knowledgeFilterDto) {
        String category = "%";
        String title = "%";
        LocalDateTime startTime = LocalDateTime.parse("0000-01-01T00:00:00");
        LocalDateTime endTime = LocalDateTime.parse("9999-12-31T23:59:59");
        Long minPrice = 0L;
        Long maxPrice = Long.MAX_VALUE;

        if(knowledgeFilterDto.getCategory() != null) {
            Category byName = categoryRepository.findByName(knowledgeFilterDto.getCategory());
            if(byName != null) category = byName.getId();
        }
        if(knowledgeFilterDto.getStartTime() != null){
            startTime = LocalDateTime.parse(knowledgeFilterDto.getStartTime());
        }
        if(knowledgeFilterDto.getEndTime() != null){
            endTime = LocalDateTime.parse(knowledgeFilterDto.getEndTime());
        }
        if(knowledgeFilterDto.getMinPrice() != null){
            minPrice = Long.parseLong(knowledgeFilterDto.getMinPrice());
        }
        if(knowledgeFilterDto.getMaxPrice() != null){
            maxPrice = Long.parseLong(knowledgeFilterDto.getMaxPrice());
        }
        if(knowledgeFilterDto.getTitle() != null){
            title = "%"+knowledgeFilterDto.getTitle()+"%";
        }

        List<Knowledge> byFilter = knowledgeRepository.findByFilter(category, startTime, endTime, minPrice, maxPrice,title);

        List<KnowledgeBriefDto> knowledgeList = new ArrayList<>();

        for(Knowledge knowledge : byFilter){
            knowledgeList.add(KnowledgeBriefDto.builder()
                    .id(knowledge.getId())
                    .title(knowledge.getTitle())
                    .thumbnail(knowledge.getThumbnail())
                    .price(String.valueOf(knowledge.getPrice()))
                    .startTime(knowledge.getStartTime().toString())
                    .endTime(knowledge.getEndTime().toString())
                    .updated_at(knowledge.getUpdatedAt().toString())
                    .build());
        }

        return knowledgeList;
    }

    @Transactional
    public Knowledge update(String email, KnowledgeUpdateDto knowledgeUpdateDto) {
        Optional<Knowledge> knowledge = knowledgeRepository.findById(knowledgeUpdateDto.getKnowledgeId());
        if(knowledge.isPresent()){
            List<Enrollment> byKnowledge = enrollmentRepository.findByKnowledge(knowledge.get());

            if(!byKnowledge.isEmpty()){
                //지식에 등록한 사람이 있으면 세션 시간 수정 불가
                knowledgeUpdateDto.setStartTime(knowledge.get().getStartTime().toString());
                knowledgeUpdateDto.setEndTime(knowledge.get().getEndTime().toString());
            }
            if(checkWriter(email,knowledge)){
                //지식 업데이트
                return updateKnowledge(knowledge.get(), knowledgeUpdateDto);
            }
            else {
                return null;
            }
        }
        return null;
    }

    public List<KnowledgeBriefDto> search(String keyword) {
        List<Knowledge> byTitleContaining = knowledgeRepository.findByTitleContains(keyword);

        List<KnowledgeBriefDto> knowledgeList = new ArrayList<>();

        for(Knowledge knowledge : byTitleContaining){
            knowledgeList.add(KnowledgeBriefDto.builder()
                    .title(knowledge.getTitle())
                    .thumbnail(knowledge.getThumbnail())
                    .price(String.valueOf(knowledge.getPrice()))
                    .startTime(knowledge.getStartTime().toString())
                    .endTime(knowledge.getEndTime().toString())
                    .build());
        }

        return knowledgeList;
    }

    private Knowledge createKnowledge(User writer, KnowledgeDto knowledge) {
        Category category = categoryRepository.findByName(knowledge.getCategory());

        return knowledgeRepository.save(Knowledge.buildKnowledge(knowledge, category, writer));
    }

    public KnowledgeDto read(String knowledgeId) {
        Optional<Knowledge> byId = knowledgeRepository.findById(knowledgeId);
        if(byId.isPresent()){
            Knowledge knowledge = byId.get();
            return KnowledgeDto.builder()
                    .title(knowledge.getTitle())
                    .thumbnail(knowledge.getThumbnail())
                    .writer(knowledge.getWriter().getName())
                    .category(knowledge.getCategory().getName())
                    .content(knowledge.getContent())
                    .price(knowledge.getPrice().toString())
                    .startTime(knowledge.getStartTime().toString())
                    .endTime(knowledge.getEndTime().toString())
                    .updated_at(knowledge.getUpdatedAt().toString())
                    .build();
        }
        return null;
    }

    @Transactional
    private Knowledge updateKnowledge(Knowledge knowledge, KnowledgeUpdateDto knowledgeUpdateDto) {
        Category category = categoryRepository.findByName(knowledgeUpdateDto.getCategory());

        knowledge.setTitle(knowledgeUpdateDto.getTitle());
        knowledge.setCategory(category);
        knowledge.setPrice(Long.parseLong(knowledgeUpdateDto.getPrice()));
        knowledge.setContent(knowledgeUpdateDto.getContent());
        knowledge.setStartTime(LocalDateTime.parse(knowledgeUpdateDto.getStartTime()));
        knowledge.setEndTime(LocalDateTime.parse(knowledgeUpdateDto.getEndTime()));

        Knowledge updateKnowledge = knowledgeRepository.save(knowledge);

        return updateKnowledge;
    }

    private boolean checkWriter(String email, Optional<Knowledge> knowledge) {
        if(knowledge.isPresent() && knowledge.isPresent()) {
            //작성자인지 확인
            User writer = knowledge.get().getWriter();
            if (!writer.getEmail().equals(email)) return false;
            else return true;
        }
        return false;
    }

    public SubscribedKnowledgeDto subscribedKnowledge(String email, Pageable pageable) {
        Page<Enrollment> enrollments = enrollmentRepository.findSubscribedUsingFetchJoin(email, pageable);

        List<KnowledgeBriefDto> knowledgeBriefDtoList = new ArrayList<>();
        for(Enrollment enrollment : enrollments.getContent()){
            knowledgeBriefDtoList.add(KnowledgeBriefDto.builder()
                            .id(enrollment.getKnowledge().getId())
                            .thumbnail(enrollment.getKnowledge().getThumbnail())
                            .title(enrollment.getKnowledge().getTitle())
                            .price(String.valueOf(enrollment.getPurchasePrice()))
                            .startTime(enrollment.getKnowledge().getStartTime().toString())
                            .endTime(enrollment.getKnowledge().getEndTime().toString())
                            .build());
        }
        return SubscribedKnowledgeDto.builder()
                        .subscribeList(knowledgeBriefDtoList)
                        .totalPage(enrollments.getTotalPages())
                        .build();
    }

    public PublishedKnowledgeDto publishedKnowledge(String email, Pageable pageable) {
        User byEmail = userRepository.findByEmail(email);
        Page<Knowledge> byWriter = knowledgeRepository.findByWriter(byEmail,pageable);
        List<KnowledgeBriefDto> knowledgeBriefDtoList = new ArrayList<>();

        for(Knowledge knowledge : byWriter.getContent()){
            knowledgeBriefDtoList.add(KnowledgeBriefDto.builder()
                    .id(knowledge.getId())
                    .title(knowledge.getTitle())
                    .thumbnail(knowledge.getThumbnail())
                    .price(String.valueOf(knowledge.getPrice()))
                    .startTime(knowledge.getStartTime().toString())
                    .endTime(knowledge.getEndTime().toString())
                    .build());
        }

        return PublishedKnowledgeDto.builder()
                .publishList(knowledgeBriefDtoList)
                .totalPage(byWriter.getTotalPages())
                .build();
    }

    @Transactional
    public void deleteByUser(String email) {
        User user = userRepository.findByEmail(email);

       knowledgeRepository.deleteByWriter(user);
    }

    public List<KnowledgeBriefDto> getRecent() {
        List<Knowledge> knowledgeList = knowledgeRepository.findTop5ByOrderByCreatedAtDesc();
        List<KnowledgeBriefDto> knowledgeBriefDtoList = new ArrayList<>();
        for(Knowledge knowledge : knowledgeList){
            knowledgeBriefDtoList.add(KnowledgeBriefDto.builder()
                            .id(knowledge.getId())
                            .title(knowledge.getTitle())
                            .thumbnail(knowledge.getThumbnail())
                            .price(String.valueOf(knowledge.getPrice()))
                            .startTime(knowledge.getStartTime().toString())
                            .endTime(knowledge.getEndTime().toString())
                            .updated_at(knowledge.getUpdatedAt().toString())
                            .build());
        }
        return knowledgeBriefDtoList;
    }
}

package com.logos.knowledge.service;

import com.logos.knowledge.domain.Category;
import com.logos.knowledge.domain.Knowledge;
import com.logos.knowledge.domain.User;
import com.logos.knowledge.dto.KnowledgeBriefDto;
import com.logos.knowledge.dto.KnowledgeDto;
import com.logos.knowledge.dto.KnowledgeFilterDto;
import com.logos.knowledge.dto.KnowledgeUpdateDto;
import com.logos.knowledge.repository.CategoryRepository;
import com.logos.knowledge.repository.KnowledgeRepository;
import com.logos.knowledge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class KnowledgeServiceImpl implements KnowledgeService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private KnowledgeRepository knowledgeRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public Knowledge create(String email, KnowledgeDto knowledge) {
        User writer = userRepository.findByEmail(email);

        return createKnowledge(writer,knowledge);
    }

    @Override
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

    @Override
    public List<KnowledgeBriefDto> filter(KnowledgeFilterDto knowledgeFilterDto) {
        String category = "-1";
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

        List<Knowledge> byFilter = knowledgeRepository.findByFilter(category, startTime, endTime, minPrice, maxPrice);

        List<KnowledgeBriefDto> knowledgeList = new ArrayList<>();

        for(Knowledge knowledge : byFilter){
            knowledgeList.add(KnowledgeBriefDto.builder()
                    .title(knowledge.getTitle())
                    .price(String.valueOf(knowledge.getPrice()))
                    .startTime(knowledge.getStartTime().toString())
                    .endTime(knowledge.getEndTime().toString())
                    .build());
        }

        return knowledgeList;
    }

    @Override
    @Transactional
    public Knowledge update(String email, KnowledgeUpdateDto knowledgeUpdateDto) {
        Optional<Knowledge> knowledge = knowledgeRepository.findById(knowledgeUpdateDto.getKnowledgeId());

        if(checkWriter(email,knowledge)){
            //지식 업데이트
            return updateKnowledge(knowledge.get(), knowledgeUpdateDto);
        }
        else {
            return null;
        }
    }

    @Override
    public List<KnowledgeBriefDto> search(String keyword) {
        List<Knowledge> byTitleContaining = knowledgeRepository.findByTitleContains(keyword);

        List<KnowledgeBriefDto> knowledgeList = new ArrayList<>();

        for(Knowledge knowledge : byTitleContaining){
            knowledgeList.add(KnowledgeBriefDto.builder()
                    .title(knowledge.getTitle())
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

    @Override
    public KnowledgeDto read(String knowledgeId) {
        Optional<Knowledge> byId = knowledgeRepository.findById(knowledgeId);
        if(byId.isPresent()){
            Knowledge knowledge = byId.get();
            return KnowledgeDto.builder()
                    .title(knowledge.getTitle())
                    .category(knowledge.getCategory().getName())
                    .content(knowledge.getContent())
                    .price(knowledge.getPrice().toString())
                    .startTime(knowledge.getStartTime().toString())
                    .endTime(knowledge.getEndTime().toString())
                    .build();
        }
        return null;
    }

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
        if(knowledge.isPresent()) {
            //작성자인지 확인
            User writer = knowledge.get().getWriter();
            if (!writer.getEmail().equals(email)) return false;
            else return true;
        }
        return false;
    }

}

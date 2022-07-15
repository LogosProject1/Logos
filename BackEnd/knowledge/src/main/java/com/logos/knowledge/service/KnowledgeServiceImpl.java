package com.logos.knowledge.service;

import com.logos.knowledge.domain.Category;
import com.logos.knowledge.domain.Knowledge;
import com.logos.knowledge.domain.User;
import com.logos.knowledge.dto.KnowledgeDto;
import com.logos.knowledge.repository.CategoryRepository;
import com.logos.knowledge.repository.KnowledgeRepository;
import com.logos.knowledge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private KnowledgeRepository knowledgeRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Knowledge create(String email, KnowledgeDto knowledge) {
        Category category = categoryRepository.findByName(knowledge.getCategory());

        Knowledge newKnowledge = Knowledge.builder()
                .id(UUID.randomUUID().toString())
                .title(knowledge.getTitle())
                .category(category)
                .price(Long.parseLong(knowledge.getPrice()))
                .content(knowledge.getContent())
                .startTime(LocalDateTime.parse(knowledge.getStartTime()))
                .endTime(LocalDateTime.parse(knowledge.getEndTime())).build();


        knowledgeRepository.save(newKnowledge);

        return newKnowledge;
    }

    @Override
    public boolean delete(String email, String knowledgeId) {
        Optional<Knowledge> byId = knowledgeRepository.findById(knowledgeId);
        if(byId.isPresent()){
            //작성자인지 확인
            User writer = byId.get().getWriter();
            if(!writer.getEmail().equals(email)) return false;

            //지식 삭제
            knowledgeRepository.deleteById(knowledgeId);
            return true;
        }
        return false;
    }
}

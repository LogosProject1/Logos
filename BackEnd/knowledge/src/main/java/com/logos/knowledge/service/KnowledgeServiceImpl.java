package com.logos.knowledge.service;

import com.logos.knowledge.domain.Category;
import com.logos.knowledge.domain.Knowledge;
import com.logos.knowledge.dto.KnowledgeDto;
import com.logos.knowledge.repository.CategoryRepository;
import com.logos.knowledge.repository.KnowledgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private KnowledgeRepository knowledgeRepository;

    @Override
    public Knowledge create(KnowledgeDto knowledge) {
        Category category = categoryRepository.findByName(knowledge.getCategory());

        Knowledge newKnowledge = Knowledge.builder().title(knowledge.getTitle())
                .category(category)
                .price(Long.parseLong(knowledge.getPrice()))
                .content(knowledge.getContent())
                .startTime(LocalDateTime.parse(knowledge.getStartTime()))
                .endTime(LocalDateTime.parse(knowledge.getEndTime())).build();


        knowledgeRepository.save(newKnowledge);

        return newKnowledge;
    }
}

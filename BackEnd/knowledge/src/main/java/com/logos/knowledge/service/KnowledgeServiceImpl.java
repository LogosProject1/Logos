package com.logos.knowledge.service;

import com.logos.knowledge.domain.Category;
import com.logos.knowledge.domain.Knowledge;
import com.logos.knowledge.domain.User;
import com.logos.knowledge.dto.KnowledgeBriefDto;
import com.logos.knowledge.dto.KnowledgeDto;
import com.logos.knowledge.repository.CategoryRepository;
import com.logos.knowledge.repository.KnowledgeRepository;
import com.logos.knowledge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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
        User writer = userRepository.findByEmail(email);

        return createKnowledge(writer,knowledge);
    }

    @Override
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
    public Knowledge update(String email, KnowledgeDto knowledgeDto, String knowledgeId) {
        Optional<Knowledge> knowledge = knowledgeRepository.findById(knowledgeId);

        if(checkWriter(email,knowledge)){
            //지식 업데이트
            return updateKnowledge(knowledge.get(), knowledgeDto);
        }
        else {
            return null;
        }
    }

    @Override
    public List<KnowledgeBriefDto> search(String keyword) {
        return knowledgeRepository.findByTitleContaining(keyword);
    }

    private Knowledge createKnowledge(User writer, KnowledgeDto knowledge) {
        Category category = categoryRepository.findByName(knowledge.getCategory());

        return knowledgeRepository.save(Knowledge.buildKnowledge(knowledge, category, writer));
    }

    private Knowledge updateKnowledge(Knowledge knowledge, KnowledgeDto knowledgeDto) {
        Category category = categoryRepository.findByName(knowledgeDto.getCategory());

        knowledge.setTitle(knowledgeDto.getTitle());
        knowledge.setCategory(category);
        knowledge.setPrice(Long.parseLong(knowledgeDto.getPrice()));
        knowledge.setContent(knowledgeDto.getContent());
        knowledge.setStartTime(LocalDateTime.parse(knowledgeDto.getStartTime()));
        knowledge.setEndTime(LocalDateTime.parse(knowledgeDto.getEndTime()));

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

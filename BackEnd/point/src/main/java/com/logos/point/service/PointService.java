package com.logos.point.service;

import com.logos.point.repository.KnowledgeRepository;
import com.logos.point.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PointService {

    private final UserRepository userRepository;
    private final KnowledgeRepository knowledgeRepository;

    public void purchaseKnowledge(String email, String knowledgeId) {
        if()
    }
}

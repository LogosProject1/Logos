package com.logos.knowledge.domain;

import com.logos.knowledge.dto.KnowledgeDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Knowledge {
    @Id
    private String id;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User writer;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Long price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    public static Knowledge buildKnowledge(KnowledgeDto knowledgeDto,Category category, User writer){
        return Knowledge.builder()
                .id(UUID.randomUUID().toString())
                .title(knowledgeDto.getTitle())
                .category(category)
                .writer(writer)
                .price(Long.parseLong(knowledgeDto.getPrice()))
                .content(knowledgeDto.getContent())
                .startTime(LocalDateTime.parse(knowledgeDto.getStartTime()))
                .endTime(LocalDateTime.parse(knowledgeDto.getEndTime())).build();
    }
}

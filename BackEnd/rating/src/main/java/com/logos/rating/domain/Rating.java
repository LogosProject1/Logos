package com.logos.rating.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {
    @Id
    private String id;

    private String knowledgeId;

    private String userEmail;

    private int rate;

    private String content;

    @Column(name="modified_at")
    private LocalDateTime modifiedAt;

    public static Rating createRating(String knowledgeId,String userEmail,int rate,String content){
        return Rating.builder()
                .id(UUID.randomUUID().toString())
                .knowledgeId(knowledgeId)
                .userEmail(userEmail)
                .rate(rate)
                .content(content)
                .modifiedAt(LocalDateTime.now())
                .build();
    }
}

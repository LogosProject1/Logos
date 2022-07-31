package com.logos.point.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Enrollment {
    @Id
    private String id;

    private String knowledgeId;

    private String userEmail;

    private LocalDateTime modified_at;

    public static Enrollment createEnrollment(String knowledgeId,String userEmail){
        return Enrollment.builder()
                .id(UUID.randomUUID().toString())
                .knowledgeId(knowledgeId)
                .userEmail(userEmail)
                .modified_at(LocalDateTime.now())
                .build();
    }
}

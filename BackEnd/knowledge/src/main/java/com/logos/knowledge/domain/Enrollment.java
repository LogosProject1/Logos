package com.logos.knowledge.domain;

import lombok.*;

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
public class Enrollment {
    @Id
    private String id;

    private String knowledgeId;

    private String userEmail;

    private Long purchasePrice;

    private LocalDateTime modified_at;
}

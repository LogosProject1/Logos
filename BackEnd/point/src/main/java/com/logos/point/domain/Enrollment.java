package com.logos.point.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

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
}

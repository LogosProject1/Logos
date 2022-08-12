package com.logos.knowledge.domain;

import lombok.*;

import javax.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY)
    private Knowledge knowledge;

    private String userEmail;

    private Long purchasePrice;

    private LocalDateTime modified_at;
}

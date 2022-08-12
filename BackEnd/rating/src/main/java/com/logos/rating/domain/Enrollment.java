package com.logos.rating.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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

    @ManyToOne(fetch = FetchType.LAZY)
    private Knowledge knowledge;

    private String userEmail;

    private Long purchasePrice;

    private LocalDateTime modified_at;
}

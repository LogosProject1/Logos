package com.logos.point.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointHistory {
    @Id
    private String id;

    private String userEmail;

    private Long amount;

    private Long remain;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Enumerated(EnumType.STRING)
    private PointHistoryType type;

    public static PointHistory createPointHistory(String userEmail, Long amount, Long remain, PointHistoryType type){
        return PointHistory.builder()
                .id(UUID.randomUUID().toString())
                .userEmail(userEmail)
                .amount(amount)
                .remain(remain)
                .modifiedAt(LocalDateTime.now())
                .type(type)
                .build();
    }
}

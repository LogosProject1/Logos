package com.logos.payment.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
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

    private LocalDateTime modified_at;

    @Enumerated(EnumType.STRING)
    private PointHistoryType type;

    public static PointHistory createPointHistory(String userEmail,Long amount,Long remain,PointHistoryType type){
        return PointHistory.builder()
                .id(UUID.randomUUID().toString())
                .userEmail(userEmail)
                .amount(amount)
                .remain(remain)
                .modified_at(LocalDateTime.now())
                .type(type)
                .build();
    }
}

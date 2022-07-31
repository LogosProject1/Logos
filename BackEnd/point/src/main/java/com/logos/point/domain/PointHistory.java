package com.logos.point.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

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

    private LocalDateTime modifyTime;

    private String type;

}

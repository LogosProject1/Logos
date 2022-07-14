package com.logos.knowledge.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Knowledge {
    @Id @GeneratedValue
    private Long id;

    private LocalDateTime startTime;

    private Long price;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;
}

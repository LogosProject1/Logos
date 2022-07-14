package com.logos.knowledge.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Knowledge {
    @Id @GeneratedValue
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User writer;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Long price;

    @Builder.Default
    @OneToMany(mappedBy = "knowledge")
    private List<KnowledgeCategory> category = new ArrayList<>();

    private LocalDateTime created_at;

    private LocalDateTime updated_at;
}

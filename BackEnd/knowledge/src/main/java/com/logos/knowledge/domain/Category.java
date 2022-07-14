package com.logos.knowledge.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Category {
    @Id
    private String id;

    private String name;

    public static Category buildCategory(String name){
        return Category.builder()
                .id(UUID.randomUUID().toString())
                .name(name)
                .build();
    }
}

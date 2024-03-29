package com.logos.session.domain;

import com.logos.session.service.UUIDGenerateUtils;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

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
                .id(UUIDGenerateUtils.makeShortUUID())
                .name(name)
                .build();
    }
}

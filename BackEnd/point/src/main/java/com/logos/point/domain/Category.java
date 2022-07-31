package com.logos.point.domain;

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
}

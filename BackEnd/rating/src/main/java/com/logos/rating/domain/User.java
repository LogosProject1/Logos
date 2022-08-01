package com.logos.rating.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="logos_user")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private String phone;

    private Long point;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    @Enumerated(EnumType.STRING)
    private UserType type;

    public void pointIncrease(Long amount){
        this.point += amount;
    }
    public void pointDecrease(Long amount) { this.point -= amount;}
}

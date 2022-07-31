package com.logos.point.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="logos_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private static User buildUser(String name, String email, String password, String phone){
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .phone(phone)
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .build();
    }
}

package com.logos.point.repository;


import com.logos.point.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;

public interface UserRepository extends JpaRepository<User,Long> {
    @Lock(LockModeType.PESSIMISTIC_READ)
    User findByEmail(String email);
}

package com.logos.auth.service;

import com.logos.auth.domain.User;
import com.logos.auth.dto.SignUpDto;
import com.logos.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUpProcess(SignUpDto signUpDto){

    }
}

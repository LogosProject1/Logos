package com.logos.auth.service;

import com.logos.auth.domain.User;
import com.logos.auth.dto.SignUpDto;
import com.logos.auth.dto.UserDto;
import com.logos.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User signUpProcess(SignUpDto signUpDto){
        signUpDto.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        User newUser = userRepository.save(User.createUser(signUpDto.getName(), signUpDto.getEmail()
                , signUpDto.getPassword(), signUpDto.getPhone()));
        return newUser;
    }

    public UserDto login(UserDto userDto){
        if(!userInfo(userDto.getEmail())){
            return null;
        }

        User user = userRepository.findByEmail(userDto.getEmail());

        if(!passwordEncoder.matches(userDto.getPassword(), user.getPassword())){
            return null;
        }

        return UserDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .type(user.getType().toString())
                .build();
    }

    public boolean userInfo(String email){
        if(userRepository.existsByEmail(email)){
            return true;
        }
        return false;
    }
}

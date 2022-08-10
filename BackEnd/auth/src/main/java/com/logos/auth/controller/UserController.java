package com.logos.auth.controller;

import com.logos.auth.domain.User;
import com.logos.auth.dto.*;
import com.logos.auth.service.JwtService;
import com.logos.auth.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    public static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestBody UserDto member) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            UserDto loginUser = userService.login(member);
            if (loginUser != null) {
                Map<String, Object> userInfoMap = new HashMap<>();
                userInfoMap.put("email", loginUser.getEmail());
                userInfoMap.put("name", loginUser.getName());
                userInfoMap.put("type", loginUser.getType());
                String token = jwtService.create(userInfoMap, "access-token");// key, data, subject
                logger.debug("로그인 토큰정보 : {}", token);
                resultMap.put("access-token", token);
                resultMap.put("message", SUCCESS);
                status = HttpStatus.ACCEPTED;
            } else {
                resultMap.put("message", FAIL);
                status = HttpStatus.ACCEPTED;
            }
        } catch (Exception e) {
            logger.error("로그인 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }

    @GetMapping("/check/{email}")
    public ResponseEntity<Map<String, Object>> checkId(
            @PathVariable("email") String email,
            HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;

        boolean isUser = userService.userInfo(email);

        if (isUser) {
            resultMap.put("message", SUCCESS);
        } else {
            resultMap.put("message", FAIL);
        }

        return new ResponseEntity<>(resultMap, status);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody @Valid SignUpDto member) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        User user = userService.signUpProcess(member);
        if(user == null){
            return new ResponseEntity<>(resultMap,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(resultMap, status);
    }

    @GetMapping("/info/{email}")
    public ResponseEntity<Map<String, Object>> getInfo(
            @PathVariable("email") String email,
            HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;

        if (request.getHeader("Authorization") == null) {
            logger.error("토큰이 없어요!!!");
            resultMap.put("message", FAIL);
            status = HttpStatus.FORBIDDEN;
            return new ResponseEntity<>(resultMap, status);
        }

        if (jwtService.isUsable(request.getHeader("Authorization").substring(7))) {
            logger.info("사용 가능한 토큰!!!");
            try {
//				로그인 사용자 정보.
                UserInfoDto userDto = userService.findByEmail(email);
                resultMap.put("userInfo", userDto);
                resultMap.put("message", SUCCESS);
                status = HttpStatus.ACCEPTED;
            } catch (Exception e) {
                logger.error("정보조회 실패 : {}", e);
                resultMap.put("message", e.getMessage());
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } else {
            logger.error("사용 불가능 토큰!!!");
            resultMap.put("message", FAIL);
            status = HttpStatus.ACCEPTED;
        }
        return new ResponseEntity<>(resultMap, status);
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> update(HttpServletRequest req, @RequestBody @Valid UserUpdateDto member, Errors errors) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<ErrorDto> errorDtoList = new ArrayList<>();
        HttpStatus status = HttpStatus.OK;

        if(errors.hasErrors()){
            for(FieldError error : errors.getFieldErrors()){
                errorDtoList.add(new ErrorDto(error.getField(),error.getDefaultMessage()));
            }
            resultMap.put("errors",errorDtoList);
            resultMap.put("message", FAIL);
        }
        else{
            String email = (String) req.getAttribute("Email");
            User updatedUser = userService.update(member, email);

            if (updatedUser != null){
                resultMap.put("message", SUCCESS);
            } else {
                //기존 패스워드 불일치
                errorDtoList.add(new ErrorDto("originPassword","기존 비밀번호와 일치하지 않습니다."));
                resultMap.put("errors",errorDtoList);
                resultMap.put("message", FAIL);
            }
        }

        return new ResponseEntity<>(resultMap, status);
    }

    @DeleteMapping("/userDelete")
    public ResponseEntity<Map<String, Object>> delete(HttpServletRequest req) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        String email = (String) req.getAttribute("Email");

        if (userService.userInfo(email)){
            userService.delete(email);
            resultMap.put("message", SUCCESS);
        } else {
            status = HttpStatus.BAD_REQUEST;
            resultMap.put("message", FAIL);
        }

        return new ResponseEntity<>(resultMap, status);
    }

}
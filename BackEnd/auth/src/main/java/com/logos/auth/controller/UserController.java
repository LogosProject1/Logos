package com.logos.auth.controller;

import com.logos.auth.domain.User;
import com.logos.auth.dto.SignUpDto;
import com.logos.auth.dto.UserDto;
import com.logos.auth.dto.UserInfoDto;
import com.logos.auth.dto.UserUpdateDto;
import com.logos.auth.service.JwtService;
import com.logos.auth.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
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
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
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

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody @Valid SignUpDto member) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        User user = userService.signUpProcess(member);
        if(user == null){
            return new ResponseEntity<>(resultMap,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
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
            return new ResponseEntity<Map<String, Object>>(resultMap, status);
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
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> update(HttpServletRequest req, @RequestBody UserUpdateDto member, HttpSession session) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        String email = (String) req.getAttribute("Email");

        if (userService.userInfo(email)){
            userService.update(member, email);
            resultMap.put("message", SUCCESS);
        } else {
            resultMap.put("message", FAIL);
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
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

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

}
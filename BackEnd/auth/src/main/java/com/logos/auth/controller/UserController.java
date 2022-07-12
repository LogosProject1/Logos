package com.logos.auth.controller;

import com.logos.auth.dto.SignUpDto;
import com.logos.auth.dto.UserDto;
import com.logos.auth.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/check/{memberId}")
    public ResponseEntity<Map<String, Object>> checkId(
            @PathVariable("memberId") String memberId,
            HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;

        boolean isUser = userService.userInfo(memberId);

        if (isUser) {
            resultMap.put("message", SUCCESS);
        } else {
            resultMap.put("message", FAIL);
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody SignUpDto member) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        userService.signUpProcess(member);

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> update(@RequestAttribute Long loginMemberId, @RequestBody MemberModifyDto member, HttpSession session) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        member.setId(loginMemberId);
        if (userService.update(member)) {
            resultMap.put("message", SUCCESS);
        } else {
            resultMap.put("message", FAIL);
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @DeleteMapping
    public ResponseEntity<Map<String, Object>> delete(@RequestAttribute Long loginMemberId) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.NO_CONTENT;

        if (userService.delete(loginMemberId)) {
            resultMap.put("message", SUCCESS);
        } else {
            resultMap.put("message", FAIL);
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
}

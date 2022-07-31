package com.logos.point.controller;

import com.logos.point.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/point")
@RequiredArgsConstructor
public class PointController {

    private final PointService pointService;

    //포인트 이력 가져오기
    @GetMapping("/history")
    public ResponseEntity<Map<String, Object>> pointHistory(HttpServletRequest req) {
        Map<String, Object> resultMap = new HashMap<>();

        return ResponseEntity.ok().body(resultMap);
    }

    //유저가 포인트로 지식 구매
    @PostMapping("purchase/{knowledgeId}")
    public ResponseEntity<Map<String,Object>> purchaseKnowledge(HttpServletRequest req, @PathVariable String knowledgeId){
        String email = (String) req.getAttribute("Email");
        try{
            pointService.purchaseKnowledge(email,knowledgeId);
        }
        catch (Exception e){

        }
    }
}

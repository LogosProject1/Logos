package com.logos.point.controller;

import com.logos.point.dto.PointHistoryDto;
import com.logos.point.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/point")
@RequiredArgsConstructor
public class PointController {

    private final PointService pointService;

    @GetMapping("/history")
    public ResponseEntity<Map<String, Object>> pointHistory(HttpServletRequest req) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        String email = (String) req.getAttribute("Email");
        try {
            List<PointHistoryDto> result = pointService.getPointHistory(email);
            resultMap.put("result", result);
            status = HttpStatus.OK;
        } catch (Exception e) {
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(resultMap, status);
    }

    @PostMapping("/{knowledgeId}")
    public ResponseEntity<Map<String, Object>> purchaseKnowledge(HttpServletRequest req, @PathVariable String knowledgeId) {
        //구매
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        String email = (String) req.getAttribute("Email");

        try{
            boolean result = pointService.purchaseKnowledge(email, knowledgeId);
            if(result) resultMap.put("result","정상적으로 등록되었습니다.");
            else resultMap.put("result","등록 중 문제가 발생하였습니다.");
            status = HttpStatus.OK;
        }
        catch(Exception e) {
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }

    @DeleteMapping("/{knowledgeId}")
    public ResponseEntity<Map<String, Object>> refundKnowledge(HttpServletRequest req,@PathVariable String knowledgeId) {
        //환불
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        String email = (String) req.getAttribute("Email");

        return new ResponseEntity<>(resultMap, status);
    }

}

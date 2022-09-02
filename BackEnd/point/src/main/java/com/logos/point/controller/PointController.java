package com.logos.point.controller;

import com.logos.point.dto.PointHistoryDto;
import com.logos.point.dto.PointRefundDto;
import com.logos.point.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Map<String, Object>> pointHistory(HttpServletRequest req, Pageable pageable) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        String email = (String) req.getAttribute("Email");
        try {
            PointHistoryDto result = pointService.getPointHistory(email,pageable);
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
            Map<String,String> result = pointService.purchaseKnowledge(email, knowledgeId);
            resultMap.put("result",result.get("result"));
            resultMap.put("message",result.get("message"));
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

        try{
            PointRefundDto result = pointService.refundKnowledge(email, knowledgeId);
            resultMap.put("result",result.isResult());
            resultMap.put("message",result.getMessage());
            status = HttpStatus.OK;
        }
        catch(Exception e) {
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(resultMap, status);
    }

}

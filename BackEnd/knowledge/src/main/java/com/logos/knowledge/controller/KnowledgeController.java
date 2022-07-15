package com.logos.knowledge.controller;

import com.logos.knowledge.domain.Knowledge;
import com.logos.knowledge.dto.KnowledgeDto;
import com.logos.knowledge.service.JwtService;
import com.logos.knowledge.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/knowledge")
public class KnowledgeController {
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";


    @Autowired
    private JwtService jwtService;

    @Autowired
    private KnowledgeService knowledgeService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createKnowledge(@RequestBody KnowledgeDto knowledge) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            Knowledge temp = knowledgeService.create(knowledge);
            if (temp != null) {
                resultMap.put("message", SUCCESS);
            } else {
                resultMap.put("message", FAIL);
            }
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
//
//    @GetMapping("/read")
//    public ResponseEntity<Map<String, Object>> getKnowledge() {
//
//    }
//
    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> updateKnowledge(@RequestBody KnowledgeDto knowledge) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            Knowledge temp = knowledgeService.update(knowledge);
            if (temp != null) {
                resultMap.put("message", SUCCESS);
            } else {
                resultMap.put("message", FAIL);
            }
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, Object>> deleteKnowledge(HttpServletRequest req, @RequestBody String knowledgeId) {
        Map<String, Object> resultMap = new HashMap<>();
        String email = (String) req.getAttribute("Email");

        HttpStatus status = null;

        try {
            if(knowledgeService.delete(email,knowledgeId)){
                resultMap.put("message", SUCCESS);
            }
            else {
                resultMap.put("message", FAIL);
            }
            status = HttpStatus.OK;
        } catch (Exception e) {
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(resultMap, status);
    }

}

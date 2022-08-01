package com.logos.rating.controller;

import com.logos.rating.dto.RatingDto;
import com.logos.rating.dto.RatingFilterDto;
import com.logos.rating.dto.RatingResultDto;
import com.logos.rating.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rating")
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;

    @PostMapping("/{knowledgeId}")
    public ResponseEntity<Map<String,Object>> ratingKnowledge(HttpServletRequest req, @PathVariable String knowledgeId,
                                                              @RequestBody RatingDto ratingDto){
        Map<String,Object> resultMap = new HashMap<>();
        String email = (String) req.getAttribute("Email");

        try{
            RatingResultDto ratingResultDto = ratingService.ratingKnowledge(email, knowledgeId, ratingDto);
            resultMap.put("result",ratingResultDto);
        }
        catch (Exception e){
            resultMap.put("message",e.getMessage());
            return ResponseEntity.internalServerError().body(resultMap);
        }
        return ResponseEntity.ok().body(resultMap);
    }

    @GetMapping("/{knowledgeId}")
    public ResponseEntity<Map<String,Object>> getKnowledgeRating(HttpServletRequest req, @PathVariable String knowledgeId){
        Map<String,Object> resultMap = new HashMap<>();

        try{
            List<RatingResultDto> ratingResultDtoList = ratingService.getKnowledgeRating(knowledgeId);
            resultMap.put("result",ratingResultDtoList);
        }
        catch (Exception e){
            resultMap.put("message",e.getMessage());
            return ResponseEntity.internalServerError().body(resultMap);
        }
        return ResponseEntity.ok().body(resultMap);
    }

    @GetMapping("/filter")
    public ResponseEntity<Map<String,Object>> getFilteredRating(HttpServletRequest req, @RequestBody RatingFilterDto ratingFilterDto){
        Map<String,Object> resultMap = new HashMap<>();
        String email = (String) req.getAttribute("Email");

        try{
        }
        catch (Exception e){
            resultMap.put("message",e.getMessage());
            return ResponseEntity.internalServerError().body(resultMap);
        }
        return ResponseEntity.ok().body(resultMap);
    }
}

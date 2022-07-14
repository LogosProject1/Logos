package com.logos.knowledge.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/knowledge")
public class KnowledgeController {
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createKnowledge() {

    }

    @GetMapping("/read")
    public ResponseEntity<Map<String, Object>> getKnowledge() {

    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> updateKnowledge() {

    }

    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, Object>> deleteKnowledge() {

    }

}

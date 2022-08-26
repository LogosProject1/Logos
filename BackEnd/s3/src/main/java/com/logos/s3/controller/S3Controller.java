package com.logos.s3.controller;

import com.logos.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/s3")
@RequiredArgsConstructor
public class S3Controller {

    private final S3Service s3Service;

    @PostMapping
    public ResponseEntity<Map<String,Object>> uploadImage(HttpServletRequest req, @RequestPart("file") MultipartFile media){
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        try{
            String imageUrl = s3Service.uploadPost(media);
            if(imageUrl == null){
                resultMap.put("message", "업로드 중 문제가 발생하였습니다.");
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
            else{
                resultMap.put("url",imageUrl);
            }

        }catch (Exception e){
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(resultMap,status);
    }
}

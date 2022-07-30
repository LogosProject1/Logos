package com.logos.payment.controller;

import com.logos.payment.dto.OrderDto;
import com.logos.payment.dto.PaymentHistoryDto;
import com.logos.payment.dto.VerifyDto;
import com.logos.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> prepareOrder(HttpServletRequest req, @RequestBody OrderDto orderDto) {
        Map<String,Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        String email = (String) req.getAttribute("Email");
        try {
            String orderId = paymentService.makeOrder(email, orderDto);
            resultMap.put("merchant_uid",orderId);
            status = HttpStatus.OK;
        }catch (Exception e){
            resultMap.put("message",e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(resultMap,status);
    }

    @PostMapping("/verify")
    public ResponseEntity<Map<String,Object>> verifyOrder(HttpServletRequest req, @RequestBody VerifyDto verifyDto){
        Map<String,Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        String email = (String) req.getAttribute("Email");
        try {
            String result = paymentService.verifyOrder(email, verifyDto);
            resultMap.put("result", result);
            status = HttpStatus.OK;
        }catch (Exception e){
            resultMap.put("message",e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(resultMap,status);
    }

    @GetMapping("/history")
    public ResponseEntity<Map<String,Object>> verifyOrder(HttpServletRequest req){
        Map<String,Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        String email = (String) req.getAttribute("Email");
        try {
            List<PaymentHistoryDto> result = paymentService.getPaymentHistory(email);
            resultMap.put("result", result);
            status = HttpStatus.OK;
        }catch (Exception e){
            resultMap.put("message",e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(resultMap,status);
    }
}

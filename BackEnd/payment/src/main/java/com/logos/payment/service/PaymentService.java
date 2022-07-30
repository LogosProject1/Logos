package com.logos.payment.service;

import com.logos.payment.domain.Payment;
import com.logos.payment.domain.ResultType;
import com.logos.payment.dto.OrderDto;
import com.logos.payment.dto.VerifyDto;
import com.logos.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public String makeOrder(String email, OrderDto orderDto) {
        //주문 레코드 만들고 ID 반환
        Payment payment = Payment.builder()
                .id(UUID.randomUUID().toString())
                .amount(Long.valueOf(orderDto.getAmount()))
                .userEmail(email)
                .startTime(LocalDateTime.now())
                .paymentType(orderDto.getPaymentType())
                .result(ResultType.PENDING)
                .build();

        paymentRepository.save(payment);

        return payment.getId();
    }

    public String verifyOrder(String email, VerifyDto verifyDto) {
        Optional<Payment> byId = paymentRepository.findById(verifyDto.getMerchant_uid());
        String result = "결제 검증 실패";
        if(byId.isEmpty()){
            return result;
        }
        Payment payment = byId.get();
        
        payment.setEndTime(LocalDateTime.now());
        
        payment.setResult(ResultType.FAILURE);
        
        if(verifyDto.getResult().equals("TRUE") 
                && email.equals(payment.getUserEmail()) 
                && payment.getAmount() == Long.valueOf(verifyDto.getAmount())){
            payment.setResult(ResultType.SUCCESS);
            result = "결제 검증 성공";
        }
        
        paymentRepository.save(payment);
        return result;
    }
}

package com.logos.payment.service;

import com.logos.payment.domain.*;
import com.logos.payment.dto.OrderDto;
import com.logos.payment.dto.PaymentHistoryDto;
import com.logos.payment.dto.VerifyDto;
import com.logos.payment.repository.PaymentRepository;
import com.logos.payment.repository.PointHistoryRepository;
import com.logos.payment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final PointHistoryRepository pointHistoryRepository;

    @Transactional
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

        log.info("주문 생성 됨 : 주문 ID - {}, 주문 EMAIL - {}, 주문 금액 -{}",payment.getId(),payment.getUserEmail(),payment.getAmount());
        paymentRepository.save(payment);

        return payment.getId();
    }

    @Transactional
    public String verifyOrder(String email, VerifyDto verifyDto) {
        Optional<Payment> byId = paymentRepository.findById(verifyDto.getMerchant_uid());
        String result = "결제 검증 실패";
        if(byId.isEmpty()){
            return result;
        }
        Payment payment = byId.get();
        
        payment.setEndTime(LocalDateTime.now());
        
        payment.setResult(ResultType.FAILURE);

        if(verifyDto.getResult().equals("true")
                && email.equals(payment.getUserEmail()) 
                && payment.getAmount().equals(Long.valueOf(verifyDto.getAmount()))){
            payment.setResult(ResultType.SUCCESS);
            //포인트 충전
            User byEmail = userRepository.findByEmail(email);
            byEmail.pointIncrease(payment.getAmount());
            userRepository.save(byEmail);

            PointHistory pointHistory = PointHistory.createPointHistory(email, payment.getAmount(), byEmail.getPoint(), PointHistoryType.INC);
            pointHistoryRepository.save(pointHistory);

            result = "결제 검증 성공";
        }
        
        paymentRepository.save(payment);


        log.info("결제 검증 결과 : 주문 ID - {}, 결과 - {}",payment.getId(),result);
        return result;
    }

    public List<PaymentHistoryDto> getPaymentHistory(String email) {
        List<Payment> byUserEmail = paymentRepository.findByUserEmail(email);
        List<PaymentHistoryDto> result = new ArrayList<>();

        for(Payment payment : byUserEmail){
            result.add(PaymentHistoryDto.builder()
                    .id(payment.getId())
                    .amount(payment.getAmount())
                    .startTime(payment.getStartTime())
                    .endTime(payment.getEndTime())
                    .result(payment.getResult())
                    .paymentType(payment.getPaymentType())
                    .build());
        }
        return result;
    }
}

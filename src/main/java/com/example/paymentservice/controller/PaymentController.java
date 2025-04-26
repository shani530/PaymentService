package com.example.paymentservice.controller;

import com.example.paymentservice.dto.RequestPaymentDto;
import com.example.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    // get payment link
    @PostMapping("/payment")
    public String getPaymentLink(@RequestBody RequestPaymentDto requestPaymentDto) {
        // Logic to create a payment link
        return paymentService.getPaymentLink(
                requestPaymentDto.getAmount(),
                requestPaymentDto.getOrderId(),
                requestPaymentDto.getCustomerName(),
                requestPaymentDto.getCustomerContact(),
                requestPaymentDto.getCustomerEmail()
        );
    }
}

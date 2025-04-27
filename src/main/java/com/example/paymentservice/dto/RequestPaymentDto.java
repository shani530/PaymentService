package com.example.paymentservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestPaymentDto {
    private Long amount;
    private String orderId;
    private String customerName;
    private String customerContact;
    private String customerEmail;

}
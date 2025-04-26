package com.example.paymentservice.service;

import com.example.paymentservice.paymentgateways.PaymentGatewayChooserStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentGatewayChooserStrategy paymentGatewayChooserStrategy;



    public String getPaymentLink(double amount, String orderId, String customerName, String customerContact, String customerEmail) {

        // Logic to create a payment link
        return paymentGatewayChooserStrategy.getPaymentLink(amount, orderId, customerName, customerContact, customerEmail);
    }
}

package com.example.paymentservice.service;

import com.example.paymentservice.paymentgateways.IPaymentGateway;
import com.example.paymentservice.paymentgateways.PaymentGatewayChooserStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentGatewayChooserStrategy paymentGatewayChooserStrategy;



    public String getPaymentLink(Long amount, String orderId, String customerName, String customerContact, String customerEmail) {
        IPaymentGateway paymentGateway = paymentGatewayChooserStrategy.getBestPaymentGateway();
        // Logic to create a payment link
        return paymentGateway.getPaymentLink(amount, orderId, customerName, customerContact, customerEmail);
    }
}

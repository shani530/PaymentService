package com.example.paymentservice.paymentgateways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentGatewayChooserStrategy {
   @Autowired
    private RazorPayPaymentGateway razorPayPaymentGateway;



    public String getPaymentLink(double amount, String orderId, String customerName, String customerContact, String customerEmail) {
        // Logic to choose the payment gateway based on some criteria
        // For simplicity, we are directly using RazorPayPaymentGateway here
        return razorPayPaymentGateway.getPaymentLink(amount, orderId, customerName, customerContact, customerEmail);
    }
}

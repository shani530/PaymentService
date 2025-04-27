package com.example.paymentservice.paymentgateways;

public interface IPaymentGateway {
    // Method to create a payment link


    String getPaymentLink(Long amount, String orderId,
                          String customerName, String customerContact, String customerEmail);
}

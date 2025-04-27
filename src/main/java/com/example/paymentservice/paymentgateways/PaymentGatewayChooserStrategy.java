package com.example.paymentservice.paymentgateways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentGatewayChooserStrategy {
   @Autowired
    private RazorPayPaymentGateway razorPayPaymentGateway;

    @Autowired
    private StripePaymentGateway stripePaymentGateway;

    public IPaymentGateway getBestPaymentGateway() {
        // Logic to choose the payment gateway based on some criteria

       return stripePaymentGateway;
    }
}

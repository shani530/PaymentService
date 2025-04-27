package com.example.paymentservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StripeWebhookController {
    @PostMapping("/stripeWebhook")
    public void handleWebhook(@RequestBody String payload) {
        // Logic to handle the webhook event
        // For example, you can parse the payload and take action based on the event type
        System.out.println("Received webhook: " + payload);

    }
}

package com.example.paymentservice.paymentgateways;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class RazorPayPaymentGateway implements IPaymentGateway {

    @Value("${razorpay.key.id}")
    private String id;

    @Value("${razorpay.key.secret}")
    private String secret;

    @Override
    public String getPaymentLink(double amount, String orderId,
                                 String customerName, String customerContact, String customerEmail) {
        {
            // Logic to create a payment link using RazorPay API
            // logic to do a post call to razorpay api : https://api.razorpay.com/v1/payment_links/
            // curl -u [YOUR_KEY_ID]:[YOUR_KEY_SECRET] \
            //-X POST https://api.razorpay.com/v1/payment_links/ \
            //-H 'Content-type: application/json' \

            String url = "https://api.razorpay.com/v1/payment_links/";
            Map<String, Object> request = new HashMap<>();
            request.put("amount", amount * 100); // amount in paise
            request.put("currency", "INR");
            request.put("accept_partial", true);
            request.put("first_min_partial_amount", 100);
            request.put("expire_by", 1745680309);
            request.put("reference_id", orderId);
            request.put("description", "Payment for policy no #23456");
            Map<String, String> customer = new HashMap<>();
            customer.put("name", customerContact);
            customer.put("contact", customerName);
            customer.put("email", customerEmail);
            request.put("customer", customer);
            Map<String, Boolean> notify = new HashMap<>();
            notify.put("sms", true);
            notify.put("email", true);
            request.put("notify", notify);
            request.put("reminder_enable", true);
            Map<String, String> notes = new HashMap<>();
            notes.put("policy_name", "Jeevan Bima");
            request.put("notes", notes);
            request.put("callback_url", "https://example-callback-url.com/");
            request.put("callback_method", "get");
            // Set up the headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("key_id", id);
            headers.set("key_secret", secret);

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    new HttpEntity<>(request, headers),
                    String.class
            );
            // Assuming the response contains a payment link


            return restTemplate.getForObject(url, String.class);

        }
    }
}


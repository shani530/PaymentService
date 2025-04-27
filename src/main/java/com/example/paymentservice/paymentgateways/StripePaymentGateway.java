package com.example.paymentservice.paymentgateways;


import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StripePaymentGateway implements IPaymentGateway {

    @Value("${stripe.api.key}")
    private String StripeKey;

    @Override
    public String getPaymentLink(Long amount, String orderId, String customerName, String customerContact, String customerEmail) {
        try {
            Stripe.apiKey = StripeKey;
            // create payment link and after successful creation of payment link , open the https://www.scaler.com/
            // link in the browser
            // Logic to create a payment link using Stripe API

            PaymentLinkCreateParams params =
                    PaymentLinkCreateParams.builder()
                            .addLineItem(
                                    PaymentLinkCreateParams.LineItem.builder()
                                            .setPrice(createPrice(amount).getId())
                                            .setQuantity(1L)

                                            .build()



                            )
                            .setAfterCompletion(
                                    PaymentLinkCreateParams.AfterCompletion.builder()
                                    .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                    .setRedirect(
                                            PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                            .setUrl("https://scaler.com").build()
                                    )
                            .build()
                            )

                            .build();


            PaymentLink paymentLink = PaymentLink.create(params);

            return paymentLink.getUrl();
        }
        catch (
                StripeException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create payment link" +e.getMessage());
        }



    }
    public Price createPrice( Long amount) throws StripeException {
        // Logic to create a price using Stripe API;

        PriceCreateParams params =
                PriceCreateParams.builder()
                        .setCurrency("usd")
                        .setUnitAmount(amount)
                        .setRecurring(
                                PriceCreateParams.Recurring.builder()
                                        .setInterval(PriceCreateParams.Recurring.Interval.MONTH)
                                        .build()
                        )
                        .setProductData(
                                    PriceCreateParams.ProductData.builder().setName("Gold Plan").build()
                        )
                        .build();

        Price price = Price.create(params);
        return price;
    }
}

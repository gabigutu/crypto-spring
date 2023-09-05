package com.crypto.exchange.controllers;

import com.crypto.exchange.clients.PaymentClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.ConnectException;

@RestController
@RequestMapping("/api/buy")
public class BuyController {

    @Autowired
    PaymentClient paymentClient;

    @PostMapping("/pay")
    public void pay(
            @RequestParam String cardNumber,
            @RequestParam String name,
            @RequestParam int cvv,
            @RequestParam Double amount
    ) {
        String cardDataResponse = paymentClient.provideCardDetails(cardNumber, name, cvv);
        System.out.println("Payment server responded to card check: " + cardDataResponse);

        // ask amount
    }

    @GetMapping("/payment-alive")
    public void paymentServerIsAlive() {
        boolean isAlive = paymentClient.isAlive();
        System.out.println("Payment server responded to isAlive: " + isAlive);
    }

    @ExceptionHandler({ConnectException.class})
    public void handleException() {
        System.err.println("Could not connect to payment server");
    }
}



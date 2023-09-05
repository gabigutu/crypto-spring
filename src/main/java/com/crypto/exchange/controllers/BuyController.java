package com.crypto.exchange.controllers;

import com.crypto.exchange.clients.PaymentClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuyController {

    @Autowired
    PaymentClient paymentClient;

    public void buy(
            @RequestParam Double amount
    ) {
        String raspunsPlata = paymentClient.pay(amount);
        System.out.println("Serverul de plata a raspuns: " + raspunsPlata);
    }
}



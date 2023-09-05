package com.crypto.exchange.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "payment", url = "http://localhost:8081")
public interface PaymentClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/alive/isAlive")
    boolean isAlive();

    // /pay?amount=1000
    @RequestMapping(method = RequestMethod.POST, value = "/api/pay/card")
    String provideCardDetails(
            @RequestParam String cardNumber,
            @RequestParam String name,
            @RequestParam int cvv);

}

package com.crypto.exchange.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "payment", url = "http://localhost:8081")
public interface PaymentClient {

    // /pay?amount=1000
    @RequestMapping(method = RequestMethod.GET, value = "/pay")
    String pay(@RequestParam Double amount);

}

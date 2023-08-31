package com.crypto.exchange.controllers;

import com.crypto.exchange.exceptions.NegativeAmountException;
import com.crypto.exchange.exceptions.OtherException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/hello")
public class HelloController extends CommonController {

    @GetMapping(value = "/satoshi")
    public String hello() {
        double rand = Math.random();
        if (rand < 0.33) throw new NegativeAmountException(-1.0);
        else if (rand < 0.66) throw new OtherException("ceva");
        return "Satoshi";
    }

    @PutMapping(value = "/satoshi")
        public String put() {
            return "Satoshi data updated";
    }

}

package com.crypto.exchange.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/hello")
public class HelloController {

    @GetMapping(value = "/satoshi")
    public String hello() {
        return "Satoshi";
    }

    @PutMapping(value = "/satoshi")
        public String put() {
            return "Satoshi data updated";
    }

}

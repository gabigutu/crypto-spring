package com.crypto.exchange.controllers;

import com.crypto.exchange.services.RatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/rates")
public class RatesController {

    @Autowired
    RatesService ratesService;

    // /api/rates/[eur]-to-[usd]?amount=[1000]
    @GetMapping(value = "/{from}-to-{to}")
    public ResponseEntity<Double> showRate(
            @PathVariable("from")
            String fromCurrency,
            @PathVariable("to")
            String toCurrency,
            @RequestParam("amount")
            Double amount
    ) {
        if (amount < 0  ) return new ResponseEntity<>(0.0, HttpStatusCode.valueOf(400));
        System.out.println("Trying to convert " + amount + " " + fromCurrency + " to " + toCurrency);
        return new ResponseEntity<>(
                ratesService.exchange(fromCurrency, toCurrency, amount),
                HttpStatusCode.valueOf(200)
        );
    }
}

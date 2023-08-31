package com.crypto.exchange.controllers;

import com.crypto.exchange.entities.Currency;
import com.crypto.exchange.entities.Rate;
import com.crypto.exchange.exceptions.CurrencyNotFoundException;
import com.crypto.exchange.exceptions.NegativeAmountException;
import com.crypto.exchange.services.CurrencySerice;
import com.crypto.exchange.services.RatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/api/rates")
public class RatesController {

    @Autowired
    RatesService ratesService;

    @Autowired
    CurrencySerice currencyService;

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
//        if (amount < 0  ) return new ResponseEntity<>(0.0, HttpStatusCode.valueOf(400));
        if (amount < 0  ) {
            throw new NegativeAmountException(amount);
        }
        System.out.println("Trying to convert " + amount + " " + fromCurrency + " to " + toCurrency);

        return new ResponseEntity<>(
                ratesService.exchange(fromCurrency, toCurrency, amount),
                HttpStatusCode.valueOf(200)
        );
    }

    // /api/rates/history-[eur]-to-[usd]
    @GetMapping(value = "/history-{from}-to-{to}")
    public ResponseEntity<List<Rate>> showRate(
            @PathVariable("from")
            String fromCurrency,
            @PathVariable("to")
            String toCurrency
    ) throws CurrencyNotFoundException {
        Currency xCurrency = this.currencyService.findByCurrency(fromCurrency.toLowerCase());
        if (xCurrency == null) {
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "First currency not found");
        }
        Currency yCurrency = this.currencyService.findByCurrency(toCurrency.toLowerCase());
        if (yCurrency == null) {
            System.err.println("Currency " + toCurrency.toLowerCase() + " is not in the repository");
            throw new CurrencyNotFoundException();
        }
        return new ResponseEntity<>(
                this.ratesService.getAllRatesFromXToY(xCurrency, yCurrency),
                HttpStatusCode.valueOf(200)
        );

    }

    @ExceptionHandler({NegativeAmountException.class})
    public void handleNegativeAmountException() {
        System.err.println("Received negative amount!");
    }



}

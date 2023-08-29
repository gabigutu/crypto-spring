package com.crypto.exchange.services;


import org.springframework.stereotype.Service;

@Service
public class RatesService {

    public Double exchange(String fromCurrency, String toCurrency, Double amount) {
        double result = Math.random() * 100; // [0.0; 100.0)
        return Double.valueOf(result);
    }
}

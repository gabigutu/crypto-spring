package com.crypto.exchange.services;


import com.crypto.exchange.entities.Currency;
import com.crypto.exchange.entities.Rate;
import com.crypto.exchange.repositories.RatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatesService {

    @Autowired
    RatesRepository ratesRepository;

    public List<Rate> getAllRatesFromXToY(Currency x, Currency y) {
        System.out.println("Received " + x.getCurrency() + " and " + y.getCurrency());
        return this.ratesRepository.getAllRatesFromXToY(x.getCurrency(), y.getCurrency());
    }

    public Double exchange(String fromCurrency, String toCurrency, Double amount) {
        double result = Math.random() * 100; // [0.0; 100.0)
        return Double.valueOf(result);
    }

}

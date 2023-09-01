package com.crypto.exchange.services;

import com.crypto.exchange.entities.Currency;
import com.crypto.exchange.entities.Rate;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("ratesServiceRandom")
//@Profile("vasilica")
public class RatesRandomService implements RatesInterface {

    public List<Rate> getAllRatesFromXToY(Currency x, Currency y) {
        System.out.println("Received " + x.getCurrency() + " and " + y.getCurrency());
        List<Rate> rateArrayList = new ArrayList<>();
        Rate rate = new Rate();
        rate.setFromCurrency(x);
        rate.setToCurrency(y);
        rate.setRateValue(Math.ceil(Math.random() * 1000));
        rateArrayList.add(rate);
        return rateArrayList;
    }

    public Double exchange(String fromCurrency, String toCurrency, Double amount) {
        double result = Math.random() * 100; // [0.0; 100.0)
        return Double.valueOf(result);
    }

}

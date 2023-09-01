package com.crypto.exchange.services;

import com.crypto.exchange.entities.Currency;
import com.crypto.exchange.entities.Rate;

import java.util.List;

public interface RatesInterface {

    List<Rate> getAllRatesFromXToY(Currency x, Currency y);
    Double exchange(String fromCurrency, String toCurrency, Double amount);

}

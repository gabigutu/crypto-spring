package com.crypto.exchange.services;

import com.crypto.exchange.entities.Currency;
import com.crypto.exchange.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencySerice {

    @Autowired
    CurrencyRepository currencyRepository;

    public Currency findByCurrency(String currencyName) {
        return this.currencyRepository.findByCurrency(currencyName);
    }

}

package com.crypto.exchange;

import com.crypto.exchange.entities.Currency;
import com.crypto.exchange.repositories.CurrencyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@DataJpaTest
@RunWith(SpringRunner.class)
public class CurrencyTests {

    @Autowired
    CurrencyRepository currencyRepository;

    @Test
    public void btcShouldBeMarkedAsCrypto() {
        Currency bitcoin = currencyRepository.findByCurrency("btc");
        assertEquals("BTC should be marked as crypto", true, bitcoin.isCrypto());
    }

}

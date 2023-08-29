package com.crypto.exchange.repositories;

import com.crypto.exchange.entities.Currency;
import com.crypto.exchange.entities.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatesRepository extends JpaRepository<Rate, Long> {

    @Query("select r from Rate r where r.fromCurrency = ?1 and r.toCurrency = ?2")
    List<Rate> getAllRatesFromXToY(String x, String y);

}

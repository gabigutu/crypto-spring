package com.crypto.exchange.repositories;

import com.crypto.exchange.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {

     Currency findByCurrency(String currency);

}

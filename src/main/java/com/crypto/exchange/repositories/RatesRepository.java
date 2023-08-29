package com.crypto.exchange.repositories;

import com.crypto.exchange.entities.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatesRepository extends JpaRepository<Rate, Object> {



}

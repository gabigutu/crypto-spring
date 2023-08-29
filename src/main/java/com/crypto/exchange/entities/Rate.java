package com.crypto.exchange.entities;

import jakarta.persistence.*;

@Table(name = "rates")
@Entity
public class Rate {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    long id;

    @ManyToOne
    @JoinColumn(name = "fromCurrencyId", referencedColumnName = "id", nullable = false)
    Currency fromCurrency; // TODO: why is it unique?

    @ManyToOne
    @JoinColumn(name = "toCurrencyId", referencedColumnName = "id", nullable = false)
    Currency toCurrency;

    @Column(nullable = false, name = "rate_value")
    double rateValue;

    @Column(name = "rate_timestamp")
    int rateTimestamp;

}

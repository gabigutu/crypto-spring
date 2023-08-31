package com.crypto.exchange.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Table(name = "rates")
@Entity
@Schema(description = "Model used for rate resource")
@Data
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

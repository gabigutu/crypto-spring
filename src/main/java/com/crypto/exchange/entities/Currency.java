package com.crypto.exchange.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

@Table(name = "currencies")
@Entity
@Data
public class Currency {

   /* # currencies: id, currency, is_crypto
            # 0, usd, 0
            # 1, eur, 0
            # 2, btc, 1
    */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String currency;

    @ColumnDefault("0")
    private boolean is_crypto;


}

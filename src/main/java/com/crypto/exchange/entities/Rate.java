package com.crypto.exchange.entities;

import jakarta.persistence.*;

@Table(name = "rates")
@Entity
public class Rate {

    @Id
    long id;

    @OneToOne
    Currency from;

    @OneToOne
    Currency to;

    @Column(nullable = false)
    double value;

    int timestamp;

}

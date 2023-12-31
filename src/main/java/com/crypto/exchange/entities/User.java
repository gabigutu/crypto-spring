package com.crypto.exchange.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "utilizatori")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true)
    private String username;

    private String password;

}

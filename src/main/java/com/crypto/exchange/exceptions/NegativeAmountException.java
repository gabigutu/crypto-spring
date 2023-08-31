package com.crypto.exchange.exceptions;

public class NegativeAmountException extends RuntimeException {

    public NegativeAmountException(Double amount) {
        super("Negative amount not allowed! You provided " + amount);
    }

}


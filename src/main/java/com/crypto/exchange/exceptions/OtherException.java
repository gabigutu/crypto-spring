package com.crypto.exchange.exceptions;

public class OtherException extends RuntimeException {

    public OtherException(String message) {
        super("Other exception: " + message);
    }

}

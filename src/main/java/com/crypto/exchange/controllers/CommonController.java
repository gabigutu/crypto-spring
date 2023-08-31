package com.crypto.exchange.controllers;

import com.crypto.exchange.exceptions.OtherException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class CommonController {

    @ExceptionHandler({OtherException.class})
    public void handleOtherException() {
        System.err.println("Received other exception");
    }
}

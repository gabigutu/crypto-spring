package com.crypto.exchange.controllers;

import com.crypto.exchange.entities.Currency;
import com.crypto.exchange.entities.Rate;
import com.crypto.exchange.exceptions.CurrencyNotFoundException;
import com.crypto.exchange.exceptions.NegativeAmountException;
import com.crypto.exchange.services.CurrencySerice;
import com.crypto.exchange.services.RatesInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/api/rates")
@Profile("dev")
public class RatesDevController {

    @Autowired
    @Qualifier("ratesServiceRandom")
    RatesInterface ratesService;

    @Autowired
    CurrencySerice currencyService;

    // /api/rates/[eur]-to-[usd]?amount=[1000]
    @GetMapping(value = "/{from}-to-{to}")
    public ResponseEntity<Double> showRate(
            @PathVariable("from")
            String fromCurrency,
            @PathVariable("to")
            String toCurrency,
            @RequestParam("amount")
            Double amount
    ) {
//        if (amount < 0  ) return new ResponseEntity<>(0.0, HttpStatusCode.valueOf(400));
        if (amount < 0  ) {
            throw new NegativeAmountException(amount);
        }
        System.out.println("Trying to convert " + amount + " " + fromCurrency + " to " + toCurrency);

        return new ResponseEntity<>(
                ratesService.exchange(fromCurrency, toCurrency, amount),
                HttpStatusCode.valueOf(200)
        );
    }

    // /api/rates/history-[eur]-to-[usd]
    @GetMapping(value = "/history-{from}-to-{to}")
    @Operation(description = "Shows a history of rates from first currency to second currency")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            arraySchema = @Schema(implementation = List.class),
                                            schema = @Schema(implementation = Rate.class)
                                    ))
                    }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
    })
    public ResponseEntity<List<Rate>> showRate(
            @PathVariable("from")
            @Parameter(description = "Currency (fiat or not) you wish to convert from", example = "EUR")
            String fromCurrency,
            @PathVariable("to")
            @Parameter(description = "Currency (fiat or not) you wish to convert to", example = "BTC")
            String toCurrency
    ) throws CurrencyNotFoundException {
        Currency xCurrency = this.currencyService.findByCurrency(fromCurrency.toLowerCase());
        if (xCurrency == null) {
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "First currency not found");
        }
        Currency yCurrency = this.currencyService.findByCurrency(toCurrency.toLowerCase());
        if (yCurrency == null) {
            System.err.println("Currency " + toCurrency.toLowerCase() + " is not in the repository");
            throw new CurrencyNotFoundException();
        }
        List<Rate> rateDAOs = this.ratesService.getAllRatesFromXToY(xCurrency, yCurrency);
        // convert rateDAOs to rateDTOs
        return new ResponseEntity<>(
                rateDAOs, // replace with rateDTOs
                HttpStatusCode.valueOf(200)
        );

    }

    @ExceptionHandler({NegativeAmountException.class})
    public void handleNegativeAmountException() {
        System.err.println("Received negative amount!");
    }

    @PostConstruct // dupa constructor
    public void afterConstructor() {
        System.out.println("After calling constructor for RatesController");
    }

    @PreDestroy // inainte de destroy
    public void beforeDestroy() {
        System.out.println("Before destroying RatesController");
    }

}

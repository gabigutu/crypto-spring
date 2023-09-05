package db.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pay")
public class PayController {

    @PostMapping("/card")
    public String provideCardData(
            @RequestParam String cardNumber,
            @RequestParam String name,
            @RequestParam Double cvv
    ) {
        System.out.println("Received card data: " + cardNumber + " (" + name + "). CVV = " + cvv);
        return "Card data saved";
    }

}

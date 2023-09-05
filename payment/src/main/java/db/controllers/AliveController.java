package db.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/alive")
public class AliveController {

    @GetMapping("/isAlive")
    public boolean isAlive() {
        return true;
    }
}

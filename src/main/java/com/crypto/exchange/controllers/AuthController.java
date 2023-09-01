package com.crypto.exchange.controllers;

import com.crypto.exchange.entities.User;
import com.crypto.exchange.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    // (username, password)
    public ResponseEntity<String> login(
            @RequestParam String username,
            @RequestParam String password,
            HttpServletRequest request,
            @RequestHeader(value = "User-Agent") String userAgent
    ) {
        System.out.println("Am primit " + username + " si " + password);
        // verific combinatia username si password in baza de date
        User user = userService.findByUsernameAndPassword(username, password);
        if (user == null) {
            return new ResponseEntity<>("Wrong credentials", HttpStatus.FORBIDDEN);
        }

        // daca combinatia este buna, creez o noua sesiune pentru utilizatoriul autentificat
        HttpSession newSession =  request.getSession();
        newSession.setAttribute("generatLa", new Date());
        newSession.setAttribute("createDe", "ExchangeServer");
        newSession.setAttribute("username", username);
        newSession.setAttribute("rol", "admin");
        newSession.setAttribute("ip", request.getRemoteAddr());
        newSession.setAttribute("userAgent", userAgent);
        System.out.println("Sesiunea creata este: " + newSession);
        ResponseCookie responseCookie = ResponseCookie.from("sesiuneata", newSession.getId()).build();

        // ii trimit clientului care a cerut request-ul identificatorul sesiunii
        return ResponseEntity.ok().header("Set-Cookie", responseCookie.toString()).build();
    }

}

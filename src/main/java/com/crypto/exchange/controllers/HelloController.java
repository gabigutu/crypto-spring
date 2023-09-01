package com.crypto.exchange.controllers;

import com.crypto.exchange.components.PrototypeStudentComponent;
import com.crypto.exchange.components.RequestStudentComponent;
import com.crypto.exchange.components.SessionStudentComponent;
import com.crypto.exchange.components.SingletonStudentComponent;
import com.crypto.exchange.entities.User;
import com.crypto.exchange.exceptions.NegativeAmountException;
import com.crypto.exchange.exceptions.OtherException;
import com.crypto.exchange.services.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Date;

@RestController
@RequestMapping(value = "/api/hello")
public class HelloController extends CommonController {

    @Autowired
    SingletonStudentComponent student1;
    @Autowired
    SingletonStudentComponent student2;

    @Autowired
    PrototypeStudentComponent prototypeStudent1;
    @Autowired
    PrototypeStudentComponent prototypeStudent2;

    @Autowired
    SessionStudentComponent sessionStudent;

    @Autowired
    RequestStudentComponent requestStudent;

    @Autowired
    UserService userService;

    @GetMapping(value = "/satoshi-session")
    public ResponseEntity<String> helloSession(
            HttpSession session,
            HttpServletRequest request,
            @RequestHeader(value = "User-Agent") String userAgent
    ) {
        System.out.println("Am primit session id = " + session.getId());
        Date generatLa = (Date)session.getAttribute("generatLa");
        String role = (String)session.getAttribute("rol");
        System.out.println("Sesiunea ta a fost creata la " + generatLa + " si tu esti " + role);
        String ipDinSesiune = (String) session.getAttribute("ip");
        if (ipDinSesiune.compareTo(request.getRemoteAddr()) != 0) {
            System.out.println("IP sesiune " + ipDinSesiune + " si ip request este " + request.getRemoteAddr());
            return new ResponseEntity<>("Ai schimbat IP-ul", HttpStatus.UNAUTHORIZED);
        }
        String userAgentDinSesiune = (String) session.getAttribute("userAgent");
        if (userAgentDinSesiune.compareTo(userAgent) != 0) {
            System.out.println("USer agent sesiune " + userAgentDinSesiune + " user agent este " + userAgent);
            return new ResponseEntity<>("Ai schimbat browser-ul", HttpStatus.UNAUTHORIZED);
        }
        if (role.compareTo("admin") != 0) {
            return new ResponseEntity<>("Nu esti admin", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping(value = "/satoshi")
    public ResponseEntity<String> hello(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader
    ) {
        // check if value starts with "Basic "
        if (!authorizationHeader.startsWith("Basic ")) {
            return new ResponseEntity<>("Trebuie sa te autentifici", HttpStatus.FORBIDDEN);
        }

        // Basic Authentication
        // trim "Basic "
        String credentialsString = authorizationHeader.substring(6);
        // split ":"
        String[] credentials = credentialsString.split(":");
        String username = credentials[0];
        String password = credentials[1];
        Base64.Decoder decoder = Base64.getDecoder();
        String decodedUsername = new String(decoder.decode(username));
        String decodedPassword = new String(decoder.decode(password));
        System.out.println("Username " + decodedUsername + " and password " + decodedPassword);
        User user = userService.findByUsernameAndPassword(decodedUsername, decodedPassword);
        if (user == null) {
            return new ResponseEntity<>("Credentiale gresite", HttpStatus.FORBIDDEN);
        }

        // base64 decode username and base64 decode password

        System.out.println("requestStudent: " + requestStudent);
        System.out.println("sessionStudent: " + sessionStudent);
//        double rand = Math.random();
//        if (rand < 0.33) throw new NegativeAmountException(-1.0);
//        else if (rand < 0.66) throw new OtherException("ceva");
        return new ResponseEntity<>("Satoshi", HttpStatus.OK);
    }

    @PutMapping(value = "/satoshi")
        public String put() {
            return "Satoshi data updated";
    }

    @PostConstruct // dupa constructor
    public void dupaConstructor() {
        System.out.println("After calling constructor for HelloController");
    }

    @PreDestroy // inainte de destroy
    public void inainteDistrugere() {
        System.out.println("Before destroying HelloController");
    }

}

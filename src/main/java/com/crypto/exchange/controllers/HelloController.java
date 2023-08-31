package com.crypto.exchange.controllers;

import com.crypto.exchange.components.PrototypeStudentComponent;
import com.crypto.exchange.components.RequestStudentComponent;
import com.crypto.exchange.components.SessionStudentComponent;
import com.crypto.exchange.components.SingletonStudentComponent;
import com.crypto.exchange.exceptions.NegativeAmountException;
import com.crypto.exchange.exceptions.OtherException;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(value = "/satoshi")
    public String hello() {
        System.out.println("requestStudent: " + requestStudent);
        System.out.println("sessionStudent: " + sessionStudent);
//        double rand = Math.random();
//        if (rand < 0.33) throw new NegativeAmountException(-1.0);
//        else if (rand < 0.66) throw new OtherException("ceva");
        return "Satoshi";
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

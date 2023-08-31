package com.crypto.exchange.components;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class SingletonStudentComponent {

    @PostConstruct // dupa constructor
    public void dupaConstructor() {
        System.out.println("After calling constructor for SingletonStudentComponent");
    }

    @PreDestroy // inainte de destroy
    public void inainteDistrugere() {
        System.out.println("Before destroying SingletonStudentComponent");
    }

}

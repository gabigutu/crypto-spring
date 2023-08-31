package com.crypto.exchange.components;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "prototype")
public class PrototypeStudentComponent {

    @PostConstruct // dupa constructor
    public void dupaConstructor() {
        System.out.println("After calling constructor for PrototypeStudentComponent");
    }

    @PreDestroy // inainte de destroy
    public void inainteDistrugere() {
        System.out.println("Before destroying PrototypeStudentComponent");
    }

}

package com.crypto.exchange.components;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class SessionStudentComponent {

    @PostConstruct // dupa constructor
    public void dupaConstructor() {
        System.out.println("After calling constructor for SessionStudentComponent");
    }

    @PreDestroy // inainte de destroy
    public void inainteDistrugere() {
        System.out.println("Before destroying SessionStudentComponent");
    }

}

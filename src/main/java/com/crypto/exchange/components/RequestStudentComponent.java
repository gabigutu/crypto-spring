package com.crypto.exchange.components;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.RequestScope;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestStudentComponent {

    @PostConstruct // dupa constructor
    public void dupaConstructor() {
        System.out.println("After calling constructor for RequestStudentComponent");
    }

    @PreDestroy // inainte de destroy
    public void inainteDistrugere() {
        System.out.println("Before destroying RequestStudentComponent");
    }
}

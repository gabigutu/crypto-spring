package com.crypto.exchange.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Bean
    public DaoAuthenticationProvider myAuthentification(){
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setUserDetailsService(myUserDetailsService);
        dao.setPasswordEncoder(new BCryptPasswordEncoder(10));
        return dao;
    }

    @Bean
    public SecurityFilterChain config(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authenticationProvider(myAuthentification())
                .authorizeHttpRequests()
                .requestMatchers(req -> (req.getRequestURI().equals("/login") || req.getRequestURI().contains("h2")))
                .permitAll()
                .requestMatchers("/**").authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/students/1")
                .permitAll();

        return http.build();
    }
}

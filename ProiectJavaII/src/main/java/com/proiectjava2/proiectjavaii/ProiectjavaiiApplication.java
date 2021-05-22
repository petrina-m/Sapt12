package com.proiectjava2.proiectjavaii;

import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProiectjavaiiApplication {

    public static void main(String[] args) {
        Configuration con = new Configuration().configure("hibernate.cfg.xml");
        SpringApplication.run(ProiectjavaiiApplication.class, args);
    }


}

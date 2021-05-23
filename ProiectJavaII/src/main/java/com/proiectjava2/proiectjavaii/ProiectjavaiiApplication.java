package com.proiectjava2.proiectjavaii;

import com.proiectjava2.proiectjavaii.model.Dictionary;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ProiectjavaiiApplication {

    public static void main(String[] args) throws IOException {
        Configuration con = new Configuration().configure("hibernate.cfg.xml");

        SpringApplication.run(ProiectjavaiiApplication.class, args);
    }


}

package com.proiectjava2.proiectjavaii;

import com.proiectjava2.proiectjavaii.model.GameRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameConfig {
    @Bean
    CommandLineRunner commandLineRunner(GameRepository repository){
        return args -> {

        };
    }
}

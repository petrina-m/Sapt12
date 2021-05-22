package com.proiectjava2.proiectjavaii.controller;

import com.proiectjava2.proiectjavaii.model.Game;
import com.proiectjava2.proiectjavaii.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public String naspa()
    {
        return "hello";
    } //de facut legatura de la business layer cu service

//    public List<Game> GetGame()
//    {
//        return
//    }
}

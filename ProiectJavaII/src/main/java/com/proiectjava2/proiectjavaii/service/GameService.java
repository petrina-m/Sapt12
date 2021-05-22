package com.proiectjava2.proiectjavaii.service;

import com.proiectjava2.proiectjavaii.model.Game;
import com.proiectjava2.proiectjavaii.model.GameRepository;
import com.proiectjava2.proiectjavaii.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }
    public List<Game> getGame()
    {
        return gameRepository.findAll();
    }

    public Game createGame(Player player1){
        Game game=new Game(player1);

        return game;
    }


}

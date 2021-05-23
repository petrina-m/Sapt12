package com.proiectjava2.proiectjavaii.controller;

import com.proiectjava2.proiectjavaii.controller.dto.ConnectRequest;
import com.proiectjava2.proiectjavaii.exception.GameNotFoundException;
import com.proiectjava2.proiectjavaii.exception.InvalidGameException;
import com.proiectjava2.proiectjavaii.exception.InvalidParamException;
import com.proiectjava2.proiectjavaii.model.Game;
import com.proiectjava2.proiectjavaii.model.GamePlay;
import com.proiectjava2.proiectjavaii.model.GameStatus;
import com.proiectjava2.proiectjavaii.model.Player;
import com.proiectjava2.proiectjavaii.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@RestController
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public GameController(GameService gameService, SimpMessagingTemplate simpMessagingTemplate) {
        this.gameService = gameService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @PostMapping("/start")
    public ResponseEntity<Game> start(@RequestBody Player player){ //i get a player

        return ResponseEntity.ok(gameService.createGame(player));
    }
    @PostMapping("/connect")
    public ResponseEntity<Game> connect(@RequestBody ConnectRequest request) throws InvalidParamException, InvalidGameException { //request created
        return ResponseEntity.ok(gameService.connectToGame(request.getPlayer(), request.getGameID()));
    }
    @PostMapping("/connect/random")
    public ResponseEntity<Game> connectRandom(@RequestBody Player player) throws GameNotFoundException {
        Game game = gameService.connectToRandomGame(player);
        simpMessagingTemplate.convertAndSend("/topic/game-progress/" + game.getGameID(), game);
        return ResponseEntity.ok(game);
    }
    @PostMapping("/gameplay")
    public ResponseEntity<Game> gamePlay(@RequestBody GamePlay request) throws InvalidGameException, GameNotFoundException {
        Game game = gameService.gamePlay(request);
        simpMessagingTemplate.convertAndSend("/topic/game-progress/" + game.getGameID(), request);
        if (game.getStatus() == GameStatus.FINISHED) {
            simpMessagingTemplate.convertAndSend("/topic/game-progress/" + game.getGameID(), game);
        }
        return ResponseEntity.ok(game);
    }


//    @GetMapping
//    public String naspa()
//    {
//        return "hello";
//    } //de facut legatura de la business layer cu service
//
//    @GetMapping
//    public List<Game> getGames(){
//        return gameService.getGame();
//    }

}

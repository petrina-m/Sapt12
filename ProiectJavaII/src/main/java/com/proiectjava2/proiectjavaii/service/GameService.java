package com.proiectjava2.proiectjavaii.service;

import com.proiectjava2.proiectjavaii.exception.GameNotFoundException;
import com.proiectjava2.proiectjavaii.exception.InvalidGameException;
import com.proiectjava2.proiectjavaii.exception.InvalidParamException;
import com.proiectjava2.proiectjavaii.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {
    private final GameRepository gameRepository;
    Dictionary dictionary;

    @Autowired
    public GameService(GameRepository gameRepository) throws IOException {
        this.gameRepository = gameRepository;
    }

    public List<Game> getGame() {
        return gameRepository.findAll();
    }

    //un jucator creaza un joc nou
    public Game createGame(Player player1) {
        Game game = new Game(player1);
        gameRepository.saveAndFlush(game); //pun noul joc in baza de date

        return game;
    }

    //al doilea jucator se conecteaza la un joc, fiindu-i dat un id de catre primul jucator
    public Game connectToGame(Player player2, Integer GameID) throws InvalidParamException, InvalidGameException {
        if (gameRepository.findById(GameID).isEmpty()) {
            //id-ul este incorect
            throw new InvalidParamException("The id introduced is wrong");

        }
        //id-ul este corect
        Game game = gameRepository.findById(GameID).get();

        if (game.getPlayer2() != null) {
            //exista deja un al doilea jucator
            throw new InvalidGameException("The game already has two players");
        }
        // jocul cu id-ul precizat exista si nu are un al doilea jucator

        game.setPlayer2(player2);
        game.setStatus(GameStatus.INPROGRGESS);
        gameRepository.saveAndFlush(game); //salvez modificarile in baza de date
        return game;
    }

    public Game connectToRandomGame(Player player2) throws GameNotFoundException {
        List<Game> gameList= gameRepository.findAll();
        Game randomGame = null;
        for (Game game : gameList) {
            if (game.getStatus().equals(GameStatus.NEW)) {
                randomGame = game;
                break;
            }
        }
        if (randomGame == null)
            throw new GameNotFoundException("Game not found");
        else {
            randomGame.setPlayer2(player2);
            randomGame.setStatus(GameStatus.INPROGRGESS);
            gameRepository.saveAndFlush(randomGame);
            return randomGame;
        }

    }

    public Game gamePlay(GamePlay gamePlay) throws GameNotFoundException, InvalidGameException {
        if (gameRepository.findById(gamePlay.getGameID()).isEmpty()) {
            //id-ul este incorect
            throw new GameNotFoundException("The id introduced is wrong");

        }
        Game game = gameRepository.findById(gamePlay.getGameID()).get();
        if (game.getStatus().equals(GameStatus.FINISHED)) {
            throw new InvalidGameException("Game already finished");
        }

        List<String> wordsUsed = game.getWordsUsed();
        long idWinner = checkWinner(wordsUsed, gamePlay.getWord(), gamePlay.getIdLastPlayer(), game.getPlayer1().getId(), game.getPlayer2().getId());

        //ultimul cuvant, id-ul playerului care a scris ultimul cuv si id players
        if (idWinner == game.getPlayer1().getId()) {
            //primul a castigat
            game.setWinner(game.getPlayer1());
            game.setStatus(GameStatus.FINISHED);
        } else if (idWinner == game.getPlayer2().getId()) {
            //al doilea a castigat
            game.setWinner(game.getPlayer2());
            game.setStatus(GameStatus.FINISHED);
        }


        //pun ultimul cuvant in lista
        wordsUsed.add(gamePlay.getWord());
        game.setWordsUsed(wordsUsed);

        //salvez schimbarile in baza de date
        gameRepository.saveAndFlush(game);

        return game;
    }

    private Boolean checkWinner(List<String> wordsUsed, String word, Long idLastPlayer, Long id) {
        //as putea returna si id-ul castigatorului sau -1 altffel
        //as putea returna true/false in functie de jucatorul pe care il verifc

        //daca cuvantul a mai fost folosit inainte de catre un jucator
        //jucatorul pentru care verficam a facut ultima miscare
        if (wordsUsed.contains(word))
            return !id.equals(idLastPlayer); //nu e castigator
        return false;
    }

    private Long checkWinner(List<String> wordsUsed, String word, Long idLastPlayer, Long FirstPlayerID, Long SecondPlayerID) {
        //as putea returna si id-ul castigatorului sau -1 altffel
        //as putea returna true/false in functie de jucatorul pe care il verifc

        //daca cuvantul a mai fost folosit inainte de catre un jucator
        if (wordsUsed.contains(word)) {
            if (FirstPlayerID.equals(idLastPlayer)) {
                //primul jucator a facut ultima miscare
                return SecondPlayerID; //primul jucator castiga
            }
            else if (SecondPlayerID.equals(idLastPlayer)) {
                return FirstPlayerID;
            }
        }

        //TODO de verificat daca cuv este din dinctionar
        if (dictionary == null) {
            try {
                dictionary = new Dictionary();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        boolean inDictionary = dictionary.contains(word);
//        System.out.println(word + " is " + (inDictionay ? "" : "NOT ") + "in dictionary!");

        if (!inDictionary) { //cuvantul nu este din dictionar
            if (FirstPlayerID.equals(idLastPlayer)) //primul jucator a facut ultima miscare
                return SecondPlayerID; //primul jucator castiga
            else if (SecondPlayerID.equals(idLastPlayer))
                return FirstPlayerID;
        }

        return -1L;
    }

    private boolean checkWord(String word)
    {

        return false;
    }
}




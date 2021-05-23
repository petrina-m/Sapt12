package com.proiectjava2.proiectjavaii.model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "games")
public class Game {
//    @SequenceGenerator(name = "game_sequence", sequenceName = "game_sequence",allocationSize =1)
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "game_sequence"
//    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Integer gameID;

    //    @OneToOne
    //    @JoinColumn(name = "player1",nullable = true)
    @OneToOne(targetEntity = Player.class, cascade = {CascadeType.ALL})
    Player player1;

//    @OneToOne
//    @JoinColumn(name = "player2",nullable = true)
    @OneToOne(targetEntity = Player.class, cascade = {CascadeType.ALL})
    Player player2;

//    @OneToOne
//    @JoinColumn(name = "winner",nullable = true)
    @OneToOne(targetEntity = Player.class, cascade = {CascadeType.ALL})
    private Player winner;

    private GameStatus status;

    @ElementCollection
    private List<String> WordsUsed;

    public Game() {

    }

    public Game(Player player1) {
        this.player1 = player1;
       // gameID= UUID.randomUUID().toString();
        List<String> WordsUsed=new ArrayList<>();
        status=GameStatus.NEW;


    }


   // @Id
    public Integer getGameID() {
        return gameID;
    }

    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }

  //  @OneToOne(targetEntity = Player.class)
    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

  //  @OneToOne(targetEntity = Player.class)
    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    //@ElementCollection
    public List<String> getWordsUsed() {
        return WordsUsed;
    }

    public void setWordsUsed(List<String> wordsUsed) {
        WordsUsed = wordsUsed;
    }

   // @OneToOne(targetEntity = Player.class)
    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
}

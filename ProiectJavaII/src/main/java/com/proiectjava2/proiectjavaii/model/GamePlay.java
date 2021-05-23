package com.proiectjava2.proiectjavaii.model;

public class GamePlay {
    private Integer gameID;
    private String word;
    private Long idLastPlayer;

    public Long getIdLastPlayer() {
        return idLastPlayer;
    }

    public void setIdLastPlayer(Long idLastPlayer) {
        this.idLastPlayer = idLastPlayer;
    }

    public Integer getGameID() {
        return gameID;
    }

    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}

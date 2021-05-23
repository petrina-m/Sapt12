package com.proiectjava2.proiectjavaii.controller.dto;

import com.proiectjava2.proiectjavaii.model.Player;

public class ConnectRequest {
    private Player player;
    private Integer GameID;

    public ConnectRequest(Player player, Integer gameID) {
        this.player = player;
        GameID = gameID;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Integer getGameID() {
        return GameID;
    }

    public void setGameID(Integer gameID) {
        GameID = gameID;
    }
}

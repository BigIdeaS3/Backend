package com.rene.models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Game {
    @Getter
    private List<Player> connectedPlayers = new ArrayList<>();

    public Game(Player player) {
        this.connectedPlayers.add(player);
    }

    public List<Player> addPlayer(Player player) {
        if (!connectedPlayers.contains(player)) {
            connectedPlayers.add(player);
            return connectedPlayers;
        } else return null;
    }

    public List<Player> removePlayer(Player player) {
        connectedPlayers.remove(player);
        return connectedPlayers;
    }

}
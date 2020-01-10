package com.rene.snakebackend.models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Game {
    @Getter
    private List<SnakePlayer> connectedPlayers = new ArrayList<>();

//    public Game(Player player) {
//        this.connectedPlayers.add(player);
//    }

    public Game() {

    }


    public List<SnakePlayer> addPlayer(SnakePlayer player) {
        if (!connectedPlayers.contains(player)) {
            connectedPlayers.add(player);
            return connectedPlayers;
        } else return null;
    }

    public List<SnakePlayer> removePlayer(SnakePlayer player) {
        connectedPlayers.remove(player);
        return connectedPlayers;
    }

    public List<SnakePlayer> updatePlayerLocation(SnakePlayer player) {

        SnakePlayer p = findByName(player.getPlayer().getUsername());

        int index = connectedPlayers.indexOf(p);

        p.setSnake(player.getSnake());

        connectedPlayers.set(index, p);

        return connectedPlayers;
    }

    public SnakePlayer findByName(String name) {
        for (SnakePlayer player : connectedPlayers) {
            if (player.getPlayer().getUsername().equals(name))
                return player;
        }
        return null;
    }



}
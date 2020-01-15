package com.rene.snakebackend.models;

import com.rene.snakebackend.interfaces.DTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Game implements DTO {
    @Getter
    private List<SnakePlayer> connectedPlayers = new ArrayList<>();
    @Getter
    @Setter
    private Location food;

    public Game() {}

    public List<SnakePlayer> addPlayer(SnakePlayer player) {
        boolean containsPlayer = false;
        for (SnakePlayer snakePlayer : connectedPlayers) {
            if (snakePlayer.getPlayer().equals(player.getPlayer())) {
                containsPlayer = true;
                break;
            }
        }
        if (!containsPlayer) {
            connectedPlayers.add(player);
            return connectedPlayers;
        }
        else return null;
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
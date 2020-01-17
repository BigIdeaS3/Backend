package com.rene.snakebackend.models;

import com.rene.snakebackend.enums.TileType;
import com.rene.snakebackend.interfaces.DTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Game implements DTO {
    @Getter
    private List<SnakePlayer> connectedPlayers = new ArrayList<>();

    @Getter
    private List<List<Location>> gameboard;

    @Getter
    private Location food;

    public void setFood(Location food) {
        this.food = food;
        gameboard.get(food.getY()).set(food.getX(), new Location(food.getX(), food.getY(), TileType.FOOD));
    }

    public Game() {
        for (int i = 0; i < 65; i++) {
            gameboard.add(new ArrayList<>());
            for (int j = 0; j < 65; j++) {
                gameboard.get(i).add(new Location(j,i, TileType.EMPTY));
            }
        }
    }

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

        for (int i = 0; i < player.getSnake().size(); i++) {
            Location body = player.getSnake().get(i);
            gameboard.get(body.getY()).set(body.getX(), new Location(body.getX(),body.getY(), TileType.SNAKEBODY));

        }

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
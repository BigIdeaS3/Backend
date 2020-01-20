package com.rene.snakebackend.commands;

import com.rene.snakebackend.enums.TileType;
import com.rene.snakebackend.interfaces.Command;
import com.rene.snakebackend.interfaces.DTO;
import com.rene.snakebackend.models.Game;
import com.rene.snakebackend.models.Location;
import com.rene.snakebackend.models.SnakePlayer;

import java.util.Arrays;

public class StartGame implements Command {

    @Override
    public Object execute(Game game, DTO message) {
        SnakePlayer p = ((SnakePlayer)message);
//        for (SnakePlayer player : game.getConnectedPlayers()) {
//            if (player.equals(p)) {
//                player.setSnake(Arrays.asList(new Location(3,0, TileType.SNAKEBODY),new Location(2,0, TileType.SNAKEBODY),new Location(1,0, TileType.SNAKEBODY),new Location(0,0, TileType.SNAKEBODY)));
//            }
//        }

        game.updatePlayerLocation(p);
        return game.getConnectedPlayers();
    }
}

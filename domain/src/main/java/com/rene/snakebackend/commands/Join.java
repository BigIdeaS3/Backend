package com.rene.snakebackend.commands;

import com.rene.snakebackend.interfaces.Command;
import com.rene.snakebackend.interfaces.DTO;
import com.rene.snakebackend.models.Game;
import com.rene.snakebackend.models.SnakePlayer;

import java.util.List;

public class Join implements Command {

    @Override
    public List<SnakePlayer> execute(Game game, DTO message) {
        return game.addPlayer((SnakePlayer) message);
    }
}

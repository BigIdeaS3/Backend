package com.rene.snakebackend.commands;


import com.rene.snakebackend.controllers.GameController;
import com.rene.snakebackend.interfaces.Command;
import com.rene.snakebackend.interfaces.DTO;
import com.rene.snakebackend.models.Game;
import com.rene.snakebackend.models.SnakePlayer;

public class Draw implements Command {
    public Draw(GameController game) {
    }

    @Override
    public Object execute(Game game, DTO message) {
        return game.updatePlayerLocation((SnakePlayer)message);
    }
}

package com.rene.snakebackend.commands;

import com.rene.snakebackend.controllers.GameController;
import com.rene.snakebackend.interfaces.Command;
import com.rene.snakebackend.interfaces.DTO;
import com.rene.snakebackend.models.Game;

public class StartGame implements Command {

    public StartGame(GameController game) {

    }

    @Override
    public Object execute(Game game, DTO message) {
        return game.getConnectedPlayers();
    }
}

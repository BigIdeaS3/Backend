package com.rene.snakebackend.commands;

import com.rene.snakebackend.interfaces.Command;
import com.rene.snakebackend.interfaces.Controller;
import com.rene.snakebackend.interfaces.DTO;
import com.rene.snakebackend.models.Game;

public class StartGame implements Command {

    private Controller controller;

    public StartGame(Controller controller) {
        this.controller = controller;
    }

    @Override
    public Object execute(Game game, DTO message) {
        return game.getConnectedPlayers();
    }
}

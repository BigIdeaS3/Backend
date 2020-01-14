package com.rene.snakebackend.commands;

import com.rene.snakebackend.interfaces.Command;
import com.rene.snakebackend.interfaces.Controller;
import com.rene.snakebackend.interfaces.DTO;
import com.rene.snakebackend.models.Game;
import com.rene.snakebackend.models.SnakePlayer;

import java.util.List;

public class GetAllPlayers implements Command {

    private Controller controller;

    public GetAllPlayers(Controller controller){
        this.controller = controller;
    }

    @Override
    public List<SnakePlayer> execute(Game game, DTO message) {
        return game.getConnectedPlayers();
    }
}

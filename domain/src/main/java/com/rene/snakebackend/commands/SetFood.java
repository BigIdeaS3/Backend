package com.rene.snakebackend.commands;

import com.rene.snakebackend.interfaces.Command;
import com.rene.snakebackend.interfaces.Controller;
import com.rene.snakebackend.interfaces.DTO;
import com.rene.snakebackend.models.Game;
import com.rene.snakebackend.models.Location;

import java.util.List;

public class SetFood implements Command {

    private Controller controller;

    public SetFood(Controller controller) {
        this.controller = controller;
    }

    @Override
    public DTO execute(Game game, DTO message) {
        game.setFood((Location)message);
        return message;
    }
}

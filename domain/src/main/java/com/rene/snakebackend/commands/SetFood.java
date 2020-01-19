package com.rene.snakebackend.commands;

import com.rene.snakebackend.interfaces.Command;
import com.rene.snakebackend.interfaces.DTO;
import com.rene.snakebackend.models.Game;
import com.rene.snakebackend.models.Location;

public class SetFood implements Command {

    @Override
    public DTO execute(Game game, DTO message) {
        game.setFood((Location)message);
        return message;
    }
}

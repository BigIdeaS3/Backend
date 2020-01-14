package com.rene.snakebackend.commands;

import com.rene.snakebackend.interfaces.Command;
import com.rene.snakebackend.interfaces.Controller;
import com.rene.snakebackend.interfaces.DTO;
import com.rene.snakebackend.models.Game;
import com.rene.snakebackend.models.Location;

public class GetFood implements Command {
    private Controller controller;
    private Object Location;

    public GetFood(Controller controller) {
        this.controller = controller;
    }

    @Override
    public Object execute(Game game, DTO message) {
        new GetFood((Controller) Location);
        return Location;
    }
}

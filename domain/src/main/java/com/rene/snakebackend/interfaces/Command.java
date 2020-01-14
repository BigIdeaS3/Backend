package com.rene.snakebackend.interfaces;

import com.rene.snakebackend.models.Game;

public interface Command {
    Object execute(Game game, DTO message);
}
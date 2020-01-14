package com.rene.snakebackend.controllers;

import com.rene.snakebackend.interfaces.Controller;
import com.rene.snakebackend.models.Game;

import java.util.ArrayList;
import java.util.List;

public class GameController implements Controller {
    List<Game> games = new ArrayList<>();

    public Game getGame(int id) {
        return games.get(id);
    }

    public List<Game> getGames() {
        return games;
    }

    public int createGame() {
        games.add(new Game());
        return games.size()-1;
    }
}

package com.rene.snakebackend.services;


import com.rene.snakebackend.models.Game;
import com.rene.snakebackend.models.Player;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
public class GameService {
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
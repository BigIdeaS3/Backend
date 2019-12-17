package com.rene.services;


import com.rene.models.Game;
import com.rene.models.Player;
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

    public int createGame(Player player) {
        games.add(new Game(player));
        return games.size()-1;
    }


}
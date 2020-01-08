package com.rene.snakebackend.components;

import com.rene.snakebackend.models.Game;
import com.rene.snakebackend.models.Player;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class GameComponent {
    public Player startGame(Game game, Player player) {
        List<Player> players = game.getConnectedPlayers();
        Player p = players.get(new Random().nextInt(players.size()));
//        game.setDrawer(p);
        return p;
    }

}
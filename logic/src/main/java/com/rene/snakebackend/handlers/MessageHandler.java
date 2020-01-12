package com.rene.snakebackend.handlers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rene.snakebackend.components.GameComponent;
import com.rene.snakebackend.enums.GameMessageType;
import com.rene.snakebackend.enums.LobbyMessageType;
import com.rene.snakebackend.models.*;
import com.rene.snakebackend.services.GameService;
import com.rene.snakebackend.services.PlayerService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MessageHandler {

    @Setter
    private GameService gameService;

    @Setter
    private GameComponent component;

    @Setter
    private PlayerService playerService;

    public MessageHandler() {
    }

    @Autowired
    public MessageHandler(GameService gameService, GameComponent component, PlayerService playerService) {
        this.gameService = gameService;
        this.component = component;
        this.playerService = playerService;
    }

    public WebsocketLobbyMessage handleLobbyMessage(WebsocketLobbyMessage msg) {
        switch (msg.getType()) {
            case CREATE:
                System.out.println("Create lobby");
                Player player = playerService.getPlayerByUserName(msg.getMessage().toString());
                int id = gameService.createGame();
                return new WebsocketLobbyMessage(LobbyMessageType.CREATE,gameService.getGame(id));
            case CONNECT:
                return new WebsocketLobbyMessage(LobbyMessageType.CONNECT, gameService.getGames());
        }
        throw new UnsupportedOperationException();
    }

    public WebsocketGameMessage handleGameMessage(int gameId, WebsocketGameMessage msg) {
            Game game = gameService.getGame(gameId);
            Gson gson =  new Gson();
            switch (msg.getType()) {
                case DRAW:
                    SnakePlayer player = gson.fromJson(msg.getMessage().toString(), SnakePlayer.class);
                    return new WebsocketGameMessage(GameMessageType.DRAW, game.updatePlayerLocation(player));
                case STARTGAME:
                    player = gson.fromJson(msg.getMessage().toString(), SnakePlayer.class);
                    game.updatePlayerLocation(player);
                    return new WebsocketGameMessage(GameMessageType.STARTGAME, game.getConnectedPlayers());
                case JOIN:
                    SnakePlayer p = new SnakePlayer(playerService.getPlayerByUserName(msg.getMessage().toString()),
                            Arrays.asList(new Location(3,0), new Location(2,0), new Location(1,0), new Location(0,0)));

                    return new WebsocketGameMessage(GameMessageType.JOIN, game.addPlayer(p));
                case GETALLPLAYERS:
                    return new WebsocketGameMessage(GameMessageType.GETALLPLAYERS, game.getConnectedPlayers());
                case SETFOOD:
                    Location food = gson.fromJson(msg.getMessage().toString(), Location.class);
                    game.setFood(food);
                    return new WebsocketGameMessage(GameMessageType.SETFOOD, game.getFood());
                case GETFOOD:
                    return new WebsocketGameMessage(GameMessageType.GETFOOD, game.getFood());

        }
        throw new UnsupportedOperationException();
    }
}
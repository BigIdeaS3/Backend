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
            switch (msg.getType()) {
            case DRAW:
                List<Location> snake = new Gson().fromJson(msg.getMessage().toString(), new TypeToken<List<Location>>(){}.getType());
                System.out.println(snake);
                return new WebsocketGameMessage(GameMessageType.DRAW, snake);
                    //Location location = new Gson().fromJson(msg.getMessage().toString(),Location.class);
                    //System.out.println(msg.getMessage());
                    //return new WebsocketGameMessage(msg.getType(),game.addPoint(location));
            case LEAVE:
                Player player = playerService.getPlayerByUserName(msg.getMessage().toString());
                return new WebsocketGameMessage(GameMessageType.LEAVE, game.removePlayer(player));

            case STARTGAME:
                player = playerService.getPlayerByUserName(msg.getMessage().toString());
                return new WebsocketGameMessage(GameMessageType.STARTGAME,component.startGame(game,player));

            case JOIN:
                player = playerService.getPlayerByUserName(msg.getMessage().toString());
                return new WebsocketGameMessage(GameMessageType.JOIN, game.addPlayer(player));

                case GETALLPLAYERS:
                    return new WebsocketGameMessage(GameMessageType.GETALLPLAYERS, game.getConnectedPlayers());

        }
        throw new UnsupportedOperationException();
    }
}
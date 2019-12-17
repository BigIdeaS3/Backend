package com.rene.handlers;

import com.rene.components.GameComponent;
import com.rene.enums.GameMessageType;
import com.rene.enums.LobbyMessageType;
import com.rene.models.Game;
import com.rene.models.Player;
import com.rene.models.WebsocketGameMessage;
import com.rene.models.WebsocketLobbyMessage;
import com.rene.services.GameService;
import com.rene.services.PlayerService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
                int id = gameService.createGame(player);
                return new WebsocketLobbyMessage(LobbyMessageType.CREATE,gameService.getGames());
            case CONNECT:
                return new WebsocketLobbyMessage(LobbyMessageType.CONNECT, gameService.getGames());
        }
        throw new UnsupportedOperationException();
    }

    public WebsocketGameMessage handleGameMessage(int gameId, WebsocketGameMessage msg) {
            Game game = gameService.getGame(gameId);
            switch (msg.getType()) {
            case LEAVE:
                Player player = playerService.getPlayerByUserName(msg.getMessage().toString());
                return new WebsocketGameMessage(GameMessageType.LEAVE, game.removePlayer(player));

            case STARTGAME:
                player = playerService.getPlayerByUserName(msg.getMessage().toString());
                return new WebsocketGameMessage(GameMessageType.STARTGAME,component.startGame(game,player));

            case JOIN:
                player = playerService.getPlayerByUserName(msg.getMessage().toString());
                return new WebsocketGameMessage(GameMessageType.JOIN, game.addPlayer(player));

        }
        throw new UnsupportedOperationException();
    }
}
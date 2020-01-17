package com.rene.snakebackend.handlers;

import com.google.gson.Gson;
import com.rene.snakebackend.commands.*;
import com.rene.snakebackend.components.GameComponent;
import com.rene.snakebackend.controllers.GameController;
import com.rene.snakebackend.enums.GameMessageType;
import com.rene.snakebackend.enums.LobbyMessageType;
import com.rene.snakebackend.enums.TileType;
import com.rene.snakebackend.interfaces.Command;
import com.rene.snakebackend.interfaces.DTO;
import com.rene.snakebackend.models.*;
import com.rene.snakebackend.services.GameService;
import com.rene.snakebackend.services.PlayerService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.EnumMap;

@Component
public class WebSocketMessageHandler implements com.rene.snakebackend.interfaces.MessageHandler {

    private final EnumMap<GameMessageType, Command> commandHashMap = new EnumMap<>(GameMessageType.class);
    private Gson gson = new Gson();
    private GameController game;


    @Setter
    private GameService gameService;

    @Setter
    private GameComponent component;

    @Setter
    private PlayerService playerService;

    public WebSocketMessageHandler() {
        registerCommands();
    }

    @Autowired
    public WebSocketMessageHandler(GameService gameService, GameComponent component, PlayerService playerService) {
        this.gameService = gameService;
        this.component = component;
        this.playerService = playerService;
        registerCommands();
    }

    private void registerCommands(){
    register(GameMessageType.DRAW, new Draw());
    register(GameMessageType.STARTGAME, new StartGame());
    register(GameMessageType.GETALLPLAYERS, new GetAllPlayers());
    register(GameMessageType.SETFOOD, new SetFood());
    register(GameMessageType.GETFOOD, new GetFood());
    register(GameMessageType.JOIN, new Join());
    register(GameMessageType.PATHFIND, new PathFind());
    }

    public WebsocketLobbyMessage handleLobbyMessage(WebsocketLobbyMessage msg) {
        switch (msg.getType()) {
            case CREATE:
                System.out.println("Create lobby");
                int id = gameService.createGame();
                return new WebsocketLobbyMessage(LobbyMessageType.CREATE,gameService.getGame(id));
            case CONNECT:
                return new WebsocketLobbyMessage(LobbyMessageType.CONNECT, gameService.getGames());
        }
        throw new UnsupportedOperationException();
    }

//    public WebsocketGameMessage handleGameMessage(int gameId, WebsocketGameMessage msg) {
//            Game game = gameService.getGame(gameId);
//            Gson gson =  new Gson();
//            switch (msg.getType()) {
//                case DRAW:
//                    SnakePlayer player = gson.fromJson(msg.getMessage().toString(), SnakePlayer.class);
//                    return new WebsocketGameMessage(GameMessageType.DRAW, game.updatePlayerLocation(player));
//                case STARTGAME:
//                    player = gson.fromJson(msg.getMessage().toString(), SnakePlayer.class);
//                    game.updatePlayerLocation(player);
//                    return new WebsocketGameMessage(GameMessageType.STARTGAME, game.getConnectedPlayers());
//                case JOIN:
//                    SnakePlayer p = new SnakePlayer(playerService.getPlayerByUserName(msg.getMessage().toString()),
//                            Arrays.asList(new Location(3,0), new Location(2,0), new Location(1,0), new Location(0,0)));
//                    return new WebsocketGameMessage(GameMessageType.JOIN, game.addPlayer(p));
//                case GETALLPLAYERS:
//                    return new WebsocketGameMessage(GameMessageType.GETALLPLAYERS, game.getConnectedPlayers());
//                case SETFOOD:
//                    Location food = gson.fromJson(msg.getMessage().toString(), Location.class);
//                    game.setFood(food);
//                    return new WebsocketGameMessage(GameMessageType.SETFOOD, game.getFood());
//                case GETFOOD:
//                    return new WebsocketGameMessage(GameMessageType.GETFOOD, game.getFood());
//
//        }
//        throw new UnsupportedOperationException();
//    }

    public void register(GameMessageType commandName, Command command) {
        commandHashMap.put(commandName,command);
    }

    @Override
    public WebsocketGameMessage execute(WebsocketGameMessage message, Integer id) {

        GameMessageType commandName = message.getType();
        Command cmd = commandHashMap.get(commandName);
        if (cmd == null) throw new IllegalStateException("No command registered for " + commandName);
        DTO dto = deserializeDTO(message.getType(), message.getMessage().toString());
        return new WebsocketGameMessage(message.getType(),cmd.execute(gameService.getGame(id), dto));
    }

    private DTO deserializeDTO(GameMessageType type, String dtoMessage) {

        switch (type) {
            case DRAW:
                return gson.fromJson(dtoMessage, SnakePlayer.class);
            case JOIN:
            case PATHFIND:
                return new SnakePlayer(playerService.getPlayerByUserName(dtoMessage),
                            Arrays.asList(new Location(3,0, TileType.SNAKEBODY), new Location(2,0, TileType.SNAKEBODY), new Location(1,0, TileType.SNAKEBODY), new Location(0,0, TileType.SNAKEBODY)));
            case GETALLPLAYERS:
            case STARTGAME:
            case GETFOOD:
                return null;
            case SETFOOD:
                return gson.fromJson(dtoMessage, Location.class);
        }
    return null;
    }
}
package com.rene.snakebackend.interfaces;

import com.rene.snakebackend.enums.GameMessageType;
import com.rene.snakebackend.models.WebsocketGameMessage;

public interface MessageHandler {
    void register(GameMessageType commandName, Command command);
    WebsocketGameMessage execute(WebsocketGameMessage message, Integer id);
}

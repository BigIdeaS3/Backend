package com.rene.snakebackend.models;

import com.rene.snakebackend.enums.LobbyMessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebsocketLobbyMessage {
    private LobbyMessageType type;
    private Object message;
}
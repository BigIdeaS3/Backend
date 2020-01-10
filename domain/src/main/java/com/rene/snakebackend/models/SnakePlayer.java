package com.rene.snakebackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SnakePlayer {
    private Player player;
    private List<Location> snake;
}

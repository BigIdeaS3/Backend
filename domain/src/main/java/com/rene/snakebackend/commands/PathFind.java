package com.rene.snakebackend.commands;

import com.rene.snakebackend.interfaces.Command;
import com.rene.snakebackend.interfaces.DTO;
import com.rene.snakebackend.models.Algorithm;
import com.rene.snakebackend.models.Game;
import com.rene.snakebackend.models.Location;
import com.rene.snakebackend.models.SnakePlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class PathFind implements Command {
    private ExecutorService executorService = Executors.newFixedThreadPool(1);
    @Override
    public Object execute(Game game, DTO message) {
        List<List<Location>> gameboard = new ArrayList<>(game.getGameboard());

        Location snakeHead = ((SnakePlayer) message).getSnake().get(0);

        Algorithm algorithm = new Algorithm(gameboard, snakeHead);

        FutureTask<Integer> futureTask = (FutureTask<Integer>) executorService.submit(algorithm);

        try {
            return futureTask.get();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}

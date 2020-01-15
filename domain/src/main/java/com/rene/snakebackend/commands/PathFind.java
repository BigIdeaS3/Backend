package com.rene.snakebackend.commands;

import com.rene.snakebackend.interfaces.Command;
import com.rene.snakebackend.interfaces.DTO;
import com.rene.snakebackend.models.Game;
import com.rene.snakebackend.models.Location;
import com.rene.snakebackend.models.SnakePlayer;

import java.util.List;

public class PathFind implements Command {
    @Override
    public Object execute(Game game, DTO message) {
        Location snakeHead = ((SnakePlayer) message).getSnake().get(0);

        Location food = game.getFood();

        moveLocation(food, snakeHead);


        return null;
    }

    public void moveLocation(Location food, Location newLoc) {
        int xDiff = food.getX() - newLoc.getX();
        int yDiff = food.getY() - newLoc.getY();

//        TODO Collision detection - is tile free
        
        if (Math.abs(xDiff) > Math.abs(yDiff)) {
            if (xDiff > 0) {
                moveLocation(food, new Location(newLoc.getX()+1, newLoc.getY(), newLoc.getType()));
                //Move positive one tile in x direction
            } else {
                moveLocation(food, new Location(newLoc.getX()-1, newLoc.getY(), newLoc.getType()));
                //move negative one tile in x direction
            }
        } else {
            if (yDiff > 0) {
                moveLocation(food, new Location(newLoc.getX(), newLoc.getY()+1, newLoc.getType()));
            } else {
                moveLocation(food, new Location(newLoc.getX(), newLoc.getY()-1, newLoc.getType()));
            }
        }
    }
}

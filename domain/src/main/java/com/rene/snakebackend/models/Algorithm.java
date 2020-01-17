package com.rene.snakebackend.models;

import com.rene.snakebackend.enums.TileType;

import java.util.List;
import java.util.concurrent.Callable;

public class Algorithm implements Callable<Integer> {
    private List<List<Location>> gameboard;
    private Location startLoc;

    public Algorithm(List<List<Location>> gameboard, Location startLoc) {
        this.gameboard = gameboard;
        this.startLoc = startLoc;
    }

    private int solve(Location loc) {
        if (traverse(loc)) {
            int counter = 0;
            for (List<Location> locations : gameboard) {
                for (Location location : locations) {
                    if (location.getType() == TileType.PATH) counter++;
                }
            }
        }
        return -1;
    }

    private boolean traverse(Location loc) {
        if (!isValid(loc)) {
            return false;
        }

        if (isEnd(loc)) {
            gameboard.get(loc.getY()).get(loc.getX()).setType(TileType.PATH);
            return true;
        } else {
            gameboard.get(loc.getY()).get(loc.getX()).setType(TileType.WALKED);
        }


        //North
        if (traverse(new Location(loc.getX()-1, loc.getY(), loc.getType()))) {
            gameboard.get(loc.getY()).get(loc.getX()).setType(TileType.PATH);
            return true;
        }
        //East
        if (traverse(new Location(loc.getX(), loc.getY()-1, loc.getType()))) {
            gameboard.get(loc.getY()).get(loc.getX()).setType(TileType.PATH);
            return true;
        }
        //South
        if (traverse(new Location(loc.getX()+1, loc.getY(), loc.getType()))) {
            gameboard.get(loc.getY()).get(loc.getX()).setType(TileType.PATH);
            return true;
        }
        //West
        if (traverse(new Location(loc.getX(), loc.getY()+1, loc.getType()))) {
            gameboard.get(loc.getY()).get(loc.getX()).setType(TileType.PATH);
            return true;
        }

        return false;
    }

    private boolean isEnd(Location loc) {
        return gameboard.get(loc.getY()).get(loc.getX()).getType() == TileType.FOOD;
    }

    private boolean isValid(Location loc) {
        return inRange(loc) && isOpen(loc) && !isTried(loc);
    }

    private boolean inRange(Location loc) {
        return inWidth(loc.getX()) && inHeight(loc.getY());
    }

    private boolean inWidth(int x) {
        return x > 0 && x < gameboard.get(0).size();
    }


    private boolean inHeight(int y) {
        return y > 0 && y < gameboard.size();
    }


    private boolean isOpen(Location loc) {
        return gameboard.get(loc.getY()).get(loc.getX()).getType() == TileType.EMPTY;
    }

    private boolean isTried(Location loc) {
        return gameboard.get(loc.getY()).get(loc.getX()).getType() == TileType.WALKED;
    }

    @Override
    public Integer call() throws Exception {
        return solve(startLoc);
    }
}

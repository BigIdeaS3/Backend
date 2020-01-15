package componentTest;

import com.rene.snakebackend.models.Game;
import com.rene.snakebackend.models.Location;
import com.rene.snakebackend.models.Player;
import com.rene.snakebackend.models.SnakePlayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class GameTest {
        Game game = new Game();
    @BeforeEach
    public void setup(){
        game.addPlayer(new SnakePlayer(new Player("test","test"),
                Arrays.asList(new Location(3,0), new Location(2,0), new Location(1,0), new Location(0,0))));
        }

    @Test
    public void addPlayer() {

        int size = game.getConnectedPlayers().size();

        game.addPlayer(new SnakePlayer(new Player("test2","test"),
                Arrays.asList(new Location(3,0), new Location(2,0), new Location(1,0), new Location(0,0))));

        Assertions.assertNotEquals(size,game.getConnectedPlayers().size());
    }

    @Test
    public void AddPlayerFailed() {

        int size = game.getConnectedPlayers().size();

        game.addPlayer(new SnakePlayer(new Player("test","test"),
                Arrays.asList(new Location(3,0), new Location(2,0), new Location(1,0), new Location(0,0))));

        Assertions.assertEquals(size,game.getConnectedPlayers().size());
    }

    @Test
    public void removePlayer() {

        int size = game.getConnectedPlayers().size();

        game.removePlayer(new SnakePlayer(new Player("test","test"),
                Arrays.asList(new Location(3,0), new Location(2,0), new Location(1,0), new Location(0,0))));


        Assertions.assertNotEquals(size,game.getConnectedPlayers().size());
    }

    @Test
    public void removePlayerFailed() {

        int size = game.getConnectedPlayers().size();

        game.removePlayer(new SnakePlayer(new Player("test3","test"),
                Arrays.asList(new Location(3,0), new Location(2,0), new Location(1,0), new Location(0,0))));


        Assertions.assertEquals(size,game.getConnectedPlayers().size());
    }

}

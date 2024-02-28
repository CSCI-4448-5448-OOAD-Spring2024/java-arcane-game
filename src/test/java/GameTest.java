import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void TestGameConstructor(){

        Maze maze = new Maze.Builder()
                .addAdventurer("Test Player1", 5.0)
                .addCreature("Test Creature1", 3.0)
                .create2x2Maze()
                .initializeAdventurerCreaturePositions(2)
                .build();

        Game testGame = new Game(maze);
        assertFalse(testGame.isGameOver());
        assertEquals(0, testGame.getNumberOfTurns());
        assertEquals(maze.getRooms().size(), testGame.getRoomsInMap().size());

    }
}

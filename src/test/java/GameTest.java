import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void TestGameConstructor(){

        List<CharacterInterface> testAdventurers = Arrays.asList(new Adventurer("Test Adventurer", 5.0));
        List<CharacterInterface> testCreatures = Arrays.asList(new Creature("Test Creature", 3.0));
        Maze maze = new Maze();
        maze.initializeRooms(3);
        Game testGame = new Game(maze);
        testGame.setEntities(testAdventurers, testCreatures);
        assertFalse(testGame.isGameOver());
        assertEquals(0, testGame.getNumberOfTurns());
        assertEquals(maze.getRooms().size(), testGame.getRoomsInMap().size());

    }
}

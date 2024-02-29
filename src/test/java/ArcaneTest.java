import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArcaneTest {
    @Test
    public void testRunGame()
    {
        Arcane arcaneTest = new Arcane();
        arcaneTest.runGame3X3Map();
        arcaneTest.runGame2X2Map();
        arcaneTest.runGameNRooms(5);
        assertNotNull(arcaneTest);
    }
    @Test
    public void testMazeBuilder(){

        AdventurerInterface testAdventurer = new Adventurer("test adventurer", 10.0);
        CharacterInterface testCreature = new Creature("test creature", 10.0);
        String testRoomName = "NorthWest";

        Maze maze = new Maze.Builder()
                .addAdventurer("Player1", 5.0)
                .addCreature("Creature1", 3.0)
                .create2x2Maze()
                .addFood("Steak")
                .addFood("Pasta")
                .addFood("Milk")
                .addFood("Chicken")
                .addFood("Fish")
                .addFood("Burrito")
                .addFood("Eggs")
                .addFood("Toast")
                .addFood("Burger")
                .addFood("Sushi")
                .initializeAdventurerCreaturePositions(2)
                .addToRoom(testAdventurer, testRoomName)
                .addToRoom(testCreature, testRoomName)
                .build();

        assertNotNull(maze);
        assertTrue(maze.getAdventurers().contains(testAdventurer));
        assertTrue(maze.getCreatures().contains(testCreature));
    }
}
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class MazeAdapterTest {
    private Maze maze;
    private MazeAdapter mazeAdapter;

    @BeforeEach
    void setUp() {
        // Set up the maze with rooms, neighbors, and contents

        Maze maze = new Maze.Builder()
                .addAdventurer("Arthur", 5.0)
                .create2x2Maze()
                .addCreature("Ainsley", 3.0)
                .addFood("Steak")
                .initializeAdventurerCreaturePositions(2)
                .build();
        mazeAdapter = new MazeAdapter(maze);
    }

    @Test
    void testGetRooms() {
        List<String> rooms = mazeAdapter.getRooms();
        assertEquals(4, rooms.size()); // Expecting 4 rooms in a 2x2 maze
        assertTrue(rooms.contains("Northwest"));
        assertTrue(rooms.contains("Northeast"));
        assertTrue(rooms.contains("Southwest"));
        assertTrue(rooms.contains("Southeast"));
    }

    @Test
    void testGetNeighborsOf() {
        List<String> neighbors = mazeAdapter.getNeighborsOf("Northwest");
        assertEquals(2, neighbors.size()); // Expecting 2 neighbors for Northwest room
        assertTrue(neighbors.contains("Northeast"));
        assertTrue(neighbors.contains("Southwest"));
    }

    @Test
    void testAddedToAnyRoom() {
        boolean adventurerFound = false;
        boolean creatureFound = false;
        boolean foodFound = false;

        List<String> rooms = mazeAdapter.getRooms();
        for (String room : rooms) {
            List<String> contents = mazeAdapter.getContents(room);
            if (contents.stream().anyMatch(s -> s.contains("Adventurer: Arthur"))) {
                adventurerFound = true;
            }
            if (contents.stream().anyMatch(s -> s.contains("Creature: Ainsley"))) {
                creatureFound = true;
            }
            if (contents.stream().anyMatch(s -> s.contains("Food: Steak"))) {
                foodFound = true;
            }
        }

        assertTrue(adventurerFound, "Adventurer 'Arthur' should be present in one of the rooms.");
        assertTrue(creatureFound, "Creature 'Ainsley' should be present in one of the rooms.");
        assertTrue(foodFound, "Food 'Steak' should be present in one of the rooms.");
    }
}

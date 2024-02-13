import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {

    @Test
    public void RoomConstructorTest(){

        String testName = "Test Room";
        Room testRoom = new Room(testName);

        assertEquals(testName, testRoom.getRoomName());
        assertEquals(0, testRoom.getNeighboringRooms().size());
        assertEquals(0, testRoom.getAdventurers().size());
        assertEquals(0, testRoom.getCreatures().size());
    }

    @Test
    public void AddNeighborRoomsTest(){

        Room neighborRoom = new Room("NeighborRoom");
        Room testRoom = new Room("Test Room");

        testRoom.addNeighboringRoom(neighborRoom);

        assertTrue(testRoom.getNeighboringRooms().contains(neighborRoom));
        assertTrue(neighborRoom.getNeighboringRooms().contains(testRoom));
    }

    @Test
    public void AdventureCreaturePresenceTest(){

        Room testRoom = new Room("Test Room");

        Adventurer testAdventurer = new Adventurer("testAdventurer", 5.0);
        Creature testCreature = new Creature("testCreature", 3.0);

        testRoom.addAdventurer(testAdventurer);
        testRoom.addCreature(testCreature);

        assertFalse(testRoom.getAdventurers().isEmpty());
        assertFalse(testRoom.getCreatures().isEmpty());

        testRoom.removeAdventurer(testAdventurer);
        testRoom.removeCreature(testCreature);
        assertFalse(testRoom.isAdventurerPresent());
        assertFalse(testRoom.isCreaturePresent());
    }
}

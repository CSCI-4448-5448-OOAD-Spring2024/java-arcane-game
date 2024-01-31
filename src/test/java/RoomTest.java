import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {

    @Test
    public void RoomConstructorTest(){

        String testName = "Test Room";
        Room testRoom = new Room(testName);

        assertEquals(testName, testRoom.getRoomName());
        assertEquals(0, testRoom.getNeighboringRooms().size());
        assertFalse(testRoom.isAdventurerPresent());
        assertFalse(testRoom.isCreaturePresent());
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

        testRoom.setAdventurerPresent();
        assertTrue(testRoom.isAdventurerPresent());
        assertFalse(testRoom.isCreaturePresent());
        testRoom.setCreaturePresent();
        assertTrue(testRoom.isCreaturePresent());
        testRoom.removeAdventurer();
        assertFalse(testRoom.isAdventurerPresent());
    }
}

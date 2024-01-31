import java.util.ArrayList;
import java.util.List;

public class Room {

    private String name;
    private List<Room> neighboringRooms;
    private boolean creaturePresent;
    private boolean adventurerPresent;

    public Room(String name) {
        this.name = name;
        this.neighboringRooms = new ArrayList<>();
        this.adventurerPresent = false;
        this.creaturePresent = false;
    }

    public String getRoomName() {
        return name;
    }

    public List<Room> getNeighboringRooms() {
        return neighboringRooms;
    }

    public void addNeighboringRoom(Room newNeighbor) {

        this.neighboringRooms.add(newNeighbor);
        newNeighbor.neighboringRooms.add(this);
    }

    public boolean isAdventurerPresent() {
        return adventurerPresent;
    }

    public boolean isCreaturePresent() {
        return creaturePresent;
    }

    public void setAdventurerPresent() {
        adventurerPresent = true;
    }

    public void setCreaturePresent() {
        creaturePresent = true;
    }

    public void removeAdventurer() {
        adventurerPresent = false;
    }

}

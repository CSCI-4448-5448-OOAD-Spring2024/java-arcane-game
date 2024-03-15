import csci.ooad.layout.IMaze;

import java.util.ArrayList;
import java.util.List;

public class MazeAdapter implements IMaze {
    private final Maze maze;
    public MazeAdapter(Maze maze) {
        this.maze = maze;
    }
    @Override
    public List<String> getRooms() {

        List<String> roomsInMap = new ArrayList<>();
        for(Room room : maze.getRooms()){
            roomsInMap.add(room.getRoomName());
        }
        return roomsInMap;
    }
    @Override
    public List<String> getNeighborsOf(String room) {

        List<String> neighboringRooms = new ArrayList<>();
        Room currentRoom = maze.getRoomByName(room);
        List<Room> neighbors = currentRoom.getNeighboringRooms();
        for(Room neighbor : neighbors){
           neighboringRooms.add(neighbor.getRoomName());
        }
        return neighboringRooms;
    }
    @Override
    public List<String> getContents(String room) {
        Room currentRoom = maze.getRoomByName(room);
        List<String> contents = new ArrayList<>();

        for (AdventurerInterface adventurer : currentRoom.getAdventurers()) {
            contents.add("Adventurer: " + adventurer.getName());
        }

        for (CharacterInterface creature : currentRoom.getCreatures()) {
            contents.add("Creature: " + creature.getName());
        }

        for (Food food : currentRoom.getFood()) {
            contents.add("Food: " + food.getFoodName());
        }

        return contents;
    }

}

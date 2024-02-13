import java.util.ArrayList;
import java.util.List;

public class Maze {

    List<Room> roomsInMaze;

    public Maze(){
        this.roomsInMaze = new ArrayList<>();
    }

    public List<Room> getRooms(){
        return roomsInMaze;
    }
    public void initializeRooms(int dimensions) {

        Room NorthWestRoom = new Room("Northwest");
        Room NorthEastRoom = new Room("Northeast");
        Room SouthWestRoom = new Room("Southwest");
        Room SouthEastRoom = new Room("Southeast");
        Room NorthRoom = new Room("North");
        Room EastRoom = new Room("East");
        Room SouthRoom = new Room("South");
        Room WestRoom = new Room("West");
        Room CenterRoom = new Room("Center");

        if(dimensions == 2) {

            NorthWestRoom.addNeighboringRoom(NorthEastRoom);
            SouthWestRoom.addNeighboringRoom(SouthEastRoom);
            NorthEastRoom.addNeighboringRoom(SouthEastRoom);
            SouthWestRoom.addNeighboringRoom(NorthWestRoom);

            roomsInMaze.add(NorthWestRoom);
            roomsInMaze.add(NorthEastRoom);
            roomsInMaze.add(SouthEastRoom);
            roomsInMaze.add(SouthWestRoom);

        } else if (dimensions == 3) {

            NorthWestRoom.addNeighboringRoom(NorthRoom);
            NorthWestRoom.addNeighboringRoom(WestRoom);
            SouthWestRoom.addNeighboringRoom(SouthRoom);
            SouthWestRoom.addNeighboringRoom(WestRoom);
            NorthEastRoom.addNeighboringRoom(EastRoom);
            NorthEastRoom.addNeighboringRoom(NorthRoom);
            SouthEastRoom.addNeighboringRoom(EastRoom);
            SouthEastRoom.addNeighboringRoom(SouthRoom);
            CenterRoom.addNeighboringRoom(WestRoom);
            CenterRoom.addNeighboringRoom(EastRoom);


            roomsInMaze.add(NorthWestRoom);
            roomsInMaze.add(NorthRoom);
            roomsInMaze.add(NorthEastRoom);
            roomsInMaze.add(WestRoom);
            roomsInMaze.add(CenterRoom);
            roomsInMaze.add(EastRoom);
            roomsInMaze.add(SouthWestRoom);
            roomsInMaze.add(SouthRoom);
            roomsInMaze.add(SouthEastRoom);

        }
    }
}

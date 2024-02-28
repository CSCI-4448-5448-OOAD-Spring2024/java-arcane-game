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

        if(dimensions == 2) {
            init2x2Maze();
        } else if (dimensions == 3) {
            init3x3Maze();
        }
        else{
            initMazeNRooms(dimensions);
        }
    }
    public void init2x2Maze(){

        Room NorthWestRoom = new Room("Northwest");
        Room NorthEastRoom = new Room("Northeast");
        Room SouthWestRoom = new Room("Southwest");
        Room SouthEastRoom = new Room("Southeast");
        NorthWestRoom.addNeighboringRoom(NorthEastRoom);
        SouthWestRoom.addNeighboringRoom(SouthEastRoom);
        NorthEastRoom.addNeighboringRoom(SouthEastRoom);
        SouthWestRoom.addNeighboringRoom(NorthWestRoom);

        roomsInMaze.add(NorthWestRoom);
        roomsInMaze.add(NorthEastRoom);
        roomsInMaze.add(SouthEastRoom);
        roomsInMaze.add(SouthWestRoom);
    }
    public void init3x3Maze(){

        Room NorthWestRoom = new Room("Northwest");
        Room NorthEastRoom = new Room("Northeast");
        Room SouthWestRoom = new Room("Southwest");
        Room SouthEastRoom = new Room("Southeast");
        Room NorthRoom = new Room("North");
        Room EastRoom = new Room("East");
        Room SouthRoom = new Room("South");
        Room WestRoom = new Room("West");
        Room CenterRoom = new Room("Center");
        NorthWestRoom.addNeighboringRoom(NorthEastRoom);
        SouthWestRoom.addNeighboringRoom(SouthEastRoom);
        NorthEastRoom.addNeighboringRoom(SouthEastRoom);
        SouthWestRoom.addNeighboringRoom(NorthWestRoom);

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
    public void initMazeNRooms(int numberOfRooms) {
        for (int i = 0; i < numberOfRooms; i++) {
            Room newRoom = new Room("Room " + (i + 1));
            roomsInMaze.add(newRoom);
        }
        for (Room room1 : roomsInMaze) {
            for (Room room2 : roomsInMaze) {
                if (room1 != room2) {
                    room1.addNeighboringRoom(room2);
                }
            }
        }
    }


    public static class Builder {
        private List<Room> rooms;

        Builder() {
            this.rooms = new ArrayList<>();
        }

        public Builder create2x2Maze(){
            Room NorthWestRoom = new Room("Northwest");
            Room NorthEastRoom = new Room("Northeast");
            Room SouthWestRoom = new Room("Southwest");
            Room SouthEastRoom = new Room("Southeast");
            NorthWestRoom.addNeighboringRoom(NorthEastRoom);
            SouthWestRoom.addNeighboringRoom(SouthEastRoom);
            NorthEastRoom.addNeighboringRoom(SouthEastRoom);
            SouthWestRoom.addNeighboringRoom(NorthWestRoom);

            rooms.add(NorthWestRoom);
            rooms.add(NorthEastRoom);
            rooms.add(SouthEastRoom);
            rooms.add(SouthWestRoom);
            return this;
        }
        public Builder create3x3Maze(){
            Room NorthWestRoom = new Room("Northwest");
            Room NorthEastRoom = new Room("Northeast");
            Room SouthWestRoom = new Room("Southwest");
            Room SouthEastRoom = new Room("Southeast");
            Room NorthRoom = new Room("North");
            Room EastRoom = new Room("East");
            Room SouthRoom = new Room("South");
            Room WestRoom = new Room("West");
            Room CenterRoom = new Room("Center");
            NorthWestRoom.addNeighboringRoom(NorthEastRoom);
            SouthWestRoom.addNeighboringRoom(SouthEastRoom);
            NorthEastRoom.addNeighboringRoom(SouthEastRoom);
            SouthWestRoom.addNeighboringRoom(NorthWestRoom);

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

            rooms.add(NorthWestRoom);
            rooms.add(NorthRoom);
            rooms.add(NorthEastRoom);
            rooms.add(WestRoom);
            rooms.add(CenterRoom);
            rooms.add(EastRoom);
            rooms.add(SouthWestRoom);
            rooms.add(SouthRoom);
            rooms.add(SouthEastRoom);
            return this;
        }
        public Builder createMazeNRooms(int numberOfRooms) {
            for (int i = 0; i < numberOfRooms; i++) {
                Room newRoom = new Room("Room " + (i + 1));
                rooms.add(newRoom);
            }
            for (Room room1 : rooms) {
                for (Room room2 : rooms) {
                    if (room1 != room2) {
                        room1.addNeighboringRoom(room2);
                    }
                }
            }
            return this;
        }

        public Maze build() {
//            validateMaze();
            Maze maze = new Maze();
            maze.roomsInMaze = this.rooms;
            return maze;
        }
    }
}

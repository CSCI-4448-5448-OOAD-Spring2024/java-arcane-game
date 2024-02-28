import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maze {
    List<Room> roomsInMaze;
    private ArrayList<AdventurerInterface> adventurers;
    private ArrayList<CharacterInterface> creatures;

    public Maze(){
        this.roomsInMaze = new ArrayList<>();
        this.adventurers = new ArrayList<>();
        this.creatures = new ArrayList<>();
    }
    public List<Room> getRooms(){
        return roomsInMaze;
    }
    public List<AdventurerInterface> getAdventurers(){return adventurers;}
    public List<CharacterInterface> getCreatures(){return creatures;}

    public static class Builder {
        private List<Room> rooms;
        private List<AdventurerInterface> adventurers;
        private List<CharacterInterface> creatures;
        Builder() {
            this.rooms = new ArrayList<>();
            this.adventurers = new ArrayList<>();
            this.creatures = new ArrayList<>();
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
            maze.roomsInMaze = new ArrayList<>(this.rooms);
            maze.adventurers = new ArrayList<>(this.adventurers);
            maze.creatures = new ArrayList<>(this.creatures);

            return maze;
        }
        public Builder initializeAdventurerCreaturePositions(int dimensions){
            if(dimensions == 2) {
                init2x2MapEntities();
            } else if (dimensions == 3) {
                init3x3MapEntities();
            }
            else{initNRoomsEntities(dimensions);}

            return this;
        }
        public Builder init2x2MapEntities(){
            Room adventurerStartingRoom = rooms.get(new Random().nextInt(rooms.size()));
            Room creatureStartingRoom = rooms.get(new Random().nextInt(rooms.size()));
            adventurerStartingRoom.addAdventurer(adventurers.get(0));
            creatureStartingRoom.addCreature(creatures.get(0));
            return this;
        }
        public Builder init3x3MapEntities(){
            for(int i = 0; i < 2; i++){
                Room adventurerStartingRoom = rooms.get(new Random().nextInt(rooms.size()));
                adventurerStartingRoom.addAdventurer(adventurers.get(i));
            }
            for(int i = 0; i < 5; i++){
                Room creatureStartingRoom = rooms.get(new Random().nextInt(rooms.size()));
                creatureStartingRoom.addCreature(creatures.get(i));
            }
            return this;
        }
        public Builder initNRoomsEntities(int n) {
            for (int i = 0; i < adventurers.size(); i++) {
                Room adventurerStartingRoom = rooms.get(new Random().nextInt(rooms.size()));
                adventurerStartingRoom.addAdventurer(adventurers.get(i));
            }
            for (int i = 0; i < creatures.size(); i++) {
                Room creatureStartingRoom = rooms.get(new Random().nextInt(rooms.size()));
                creatureStartingRoom.addCreature(creatures.get(i));
            }
            return this;
        }
        public Builder addAdventurer(String name, double health) {
            adventurers.add(AdventurerFactory.createAdventurer(name, health));
            return this;
        }
        public Builder addCoward(String name, double health) {
            adventurers.add(AdventurerFactory.createCoward(name, health));
            return this;
        }
        public Builder addKnight(String name, double health) {
            adventurers.add(AdventurerFactory.createKnight(name, health));
            return this;
        }
        public Builder addGlutton(String name, double health) {
            adventurers.add(AdventurerFactory.createGlutton(name, health));
            return this;
        }
        public Builder addCreature(String name, double health) {
            creatures.add(CreatureFactory.createCreature(name, health));
            return this;
        }
        public Builder addDemon(String name, double health) {
            creatures.add(CreatureFactory.createDemon(name, health));
            return this;
        }

        public Builder addFood(String name) {
            Room roomForFood = rooms.get(new Random().nextInt(rooms.size()));
            roomForFood.addFood(FoodFactory.createFood(name));
            return this;
        }

    }
}

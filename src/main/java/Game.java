import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private boolean isOver;
    private List<Room> roomsInMap;
    private int numberOfTurns;
    private CharacterInterface adventurer;
    private CharacterInterface creature;

    public Game() {
        this.isOver = false;
        this.roomsInMap = new ArrayList<>();
        this.numberOfTurns = 0;
        this.adventurer = null;
        this.creature = null;
    }

    public void setEntities(CharacterInterface adventurer, CharacterInterface creature){

        this.adventurer = adventurer;
        this.creature = creature;
    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public List<Room> getRoomsInMap(){
        return roomsInMap;
    }

    public void createMap() {

        initializeRooms();
        initializeAdventurerCreaturePositions();
    }

    public void initializeRooms(){
        Room NorthWestRoom = new Room("Northwest");
        Room NorthEastRoom = new Room("Northeast");
        Room SouthWestRoom = new Room("Southwest");
        Room SouthEastRoom = new Room("Southeast");

        NorthWestRoom.addNeighboringRoom(NorthEastRoom);
        SouthWestRoom.addNeighboringRoom(SouthEastRoom);
        NorthEastRoom.addNeighboringRoom(SouthEastRoom);
        SouthWestRoom.addNeighboringRoom(NorthWestRoom);

        roomsInMap.add(NorthWestRoom);
        roomsInMap.add(NorthEastRoom);
        roomsInMap.add(SouthEastRoom);
        roomsInMap.add(SouthWestRoom);
    }

    public void initializeAdventurerCreaturePositions(){

        Room adventurerStartingRoom = roomsInMap.get(new Random().nextInt(roomsInMap.size()));
        Room creatureStartingRoom = roomsInMap.get(new Random().nextInt(roomsInMap.size()));

        adventurerStartingRoom.setAdventurerPresent();
        creatureStartingRoom.setCreaturePresent();

    }

    public void playGame() {
        while (!isGameOver()) {

            doTurn();
        }
    }

    public void doTurn() {

        numberOfTurns++;
        System.out.println("ARCANE MAZE: turn " + numberOfTurns);

        for (Room currentRoom : roomsInMap) {

            if (currentRoom.isAdventurerPresent()) {
                if (currentRoom.isCreaturePresent()) {
                    fight();
                } else {
                    movePlayer(currentRoom);
                }
            }
        }

        displayGameStatus();

    }

    public boolean isGameOver() {

        if (adventurer.getHealth() <= 0 || creature.getHealth() <= 0) {
            return this.isOver = true;
        }
        return this.isOver = false;
    }

    public void fight() {

        int adventurerDiceRoll = diceRoll();
        int creatureDiceRoll = diceRoll();

        if (!(adventurerDiceRoll == creatureDiceRoll)) {

            if (adventurerDiceRoll > creatureDiceRoll) {
                creature.subtractHealth(adventurerDiceRoll);
            } else {
                adventurer.subtractHealth(creatureDiceRoll);
            }
        }


    }

    public void displayGameStatus() {

        for (Room currentRoom : roomsInMap) {
            System.out.println("\t" + currentRoom.getRoomName() + ":");
            if (currentRoom.isAdventurerPresent()) {
                System.out.println("\t\tAdventurer " + adventurer.getName() + "(health: " + adventurer.getHealth() + ") is here");
            }
            if (currentRoom.isCreaturePresent()) {
                System.out.println("\t\tCreature " + creature.getName() + "(health: " + creature.getHealth() + ") is here");
            }
        }
        System.out.println();
    }

    public void movePlayer(Room room) {

        List<Room> availableRooms = room.getNeighboringRooms();
        Room newRoom = availableRooms.get(new Random().nextInt(availableRooms.size()));
        room.removeAdventurer();
        newRoom.setAdventurerPresent();

    }

    public int diceRoll(){
        return new Random().nextInt(6) + 1;
    }

    public String announceWinner(){

        if(adventurer.getHealth() == 0){
            System.out.println("Creature won!\n");
            return creature.getName();
        }
        else{
            System.out.println("Adventurer won!\n");
            return adventurer.getName();
        }
    }
}



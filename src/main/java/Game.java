import java.util.*;
//https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html

public class Game {
    private boolean isOver;
    private final List<Room> roomsInMap;
    private int numberOfTurns;
    private List<CharacterInterface> adventurers;
    private List<CharacterInterface> creatures;

    public Game() {
        this.isOver = false;
        this.roomsInMap = new ArrayList<>();
        this.numberOfTurns = 0;
        this.adventurers = new ArrayList<>();
        this.creatures = new ArrayList<>();
    }

    public void setEntities(List<CharacterInterface> adventurerList, List<CharacterInterface> creatureList){

        this.adventurers = new ArrayList<>(adventurerList);
        this.creatures = new ArrayList<>(creatureList);
    }

    public void initFood(List<Food> foodItems){

        for (Food food : foodItems) {

            Room roomForFood = roomsInMap.get(new Random().nextInt(roomsInMap.size()));
            roomForFood.addFood(food);
        }
    }
    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public List<Room> getRoomsInMap(){
        return roomsInMap;
    }

    public List<CharacterInterface> getAdventurers() {
        return adventurers;
    }

    public List<CharacterInterface> getCreatures() {
        return creatures;
    }
    public void createMap(int dimensions) {

        initializeRooms(dimensions);
        initializeAdventurerCreaturePositions(dimensions);
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

            roomsInMap.add(NorthWestRoom);
            roomsInMap.add(NorthEastRoom);
            roomsInMap.add(SouthEastRoom);
            roomsInMap.add(SouthWestRoom);

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


            roomsInMap.add(NorthWestRoom);
            roomsInMap.add(NorthRoom);
            roomsInMap.add(NorthEastRoom);
            roomsInMap.add(WestRoom);
            roomsInMap.add(CenterRoom);
            roomsInMap.add(EastRoom);
            roomsInMap.add(SouthWestRoom);
            roomsInMap.add(SouthRoom);
            roomsInMap.add(SouthEastRoom);

        }
    }

    public void initializeAdventurerCreaturePositions(int dimensions){

        if(dimensions == 2) {
            Room adventurerStartingRoom = roomsInMap.get(new Random().nextInt(roomsInMap.size()));
            Room creatureStartingRoom = roomsInMap.get(new Random().nextInt(roomsInMap.size()));

            adventurerStartingRoom.addAdventurer(adventurers.get(0));
            creatureStartingRoom.addCreature(creatures.get(0));

        } else if (dimensions == 3) {

            for(int i = 0; i < 2; i++){
                Room adventurerStartingRoom = roomsInMap.get(new Random().nextInt(roomsInMap.size()));
                adventurerStartingRoom.addAdventurer(adventurers.get(i));
            }
            for(int i = 0; i < 5; i++){
                Room creatureStartingRoom = roomsInMap.get(new Random().nextInt(roomsInMap.size()));
                creatureStartingRoom.addCreature(creatures.get(i));
            }
        }



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
                    fight(currentRoom);
                } else {
                    movePlayer(currentRoom);
                }
            }
        }
        displayGameStatus();
    }

    private CharacterInterface findHealthiestPlayer(List<CharacterInterface> entities){

        CharacterInterface healthiest = entities.get(0);
        for(CharacterInterface character : entities){
            if(character.getHealth() > healthiest.getHealth()){
                healthiest = character;
            }
        }

        return healthiest;
    }
    public boolean isGameOver() {

        boolean allAdventurersDead = true;
        for (CharacterInterface adventurer : this.adventurers) {
            if (adventurer.getHealth() > 0) {
                allAdventurersDead = false;
                break;
            }
        }

        boolean allCreaturesDead = true;
        for (CharacterInterface creature : this.creatures) {
            if (creature.getHealth() > 0) {
                allCreaturesDead = false;
                break;
            }
        }

        return this.isOver = allAdventurersDead || allCreaturesDead;
    }

    public void fight(Room currentRoom) {

        int adventurerDiceRoll = diceRoll();
        int creatureDiceRoll = diceRoll();
        CharacterInterface healthiestAdventurer = findHealthiestPlayer(currentRoom.getAdventurers());
        CharacterInterface healthiestCreature = findHealthiestPlayer(currentRoom.getCreatures());

        if (!(adventurerDiceRoll == creatureDiceRoll)) {

            if (adventurerDiceRoll > creatureDiceRoll) {
                healthiestCreature.subtractHealth(adventurerDiceRoll);
                if(!healthiestCreature.isAlive()){
                    currentRoom.removeCreature(healthiestCreature);
                    creatures.remove(healthiestCreature);
                }
            } else {
                healthiestAdventurer.subtractHealth(creatureDiceRoll);
                if(!healthiestAdventurer.isAlive()){
                    currentRoom.removeAdventurer(healthiestAdventurer);
                    adventurers.remove(healthiestAdventurer);
                }
            }
        }
    }

    public void displayGameStatus() {

        for (Room currentRoom : roomsInMap) {
            System.out.println("\t" + currentRoom.getRoomName() + ":");

            List<CharacterInterface> adventurersInRoom = currentRoom.getAdventurers();
            List<CharacterInterface> creaturesInRoom = currentRoom.getCreatures();

            for (CharacterInterface adventurer : adventurersInRoom) {
                System.out.println("\t\tAdventurer " + adventurer.getName() + "(health: " + adventurer.getHealth() + ") is here");
            }

            for (CharacterInterface creature : creaturesInRoom) {
                System.out.println("\t\tCreature " + creature.getName() + "(health: " + creature.getHealth() + ") is here");
            }
        }
        System.out.println();
    }

    public void movePlayer(Room room) {
        List<Room> availableRooms = room.getNeighboringRooms();
        List<CharacterInterface> adventurersInRoom = new ArrayList<>(room.getAdventurers());

        Iterator<CharacterInterface> iterator = adventurersInRoom.iterator();
        while (iterator.hasNext()) {
            CharacterInterface adventurer = iterator.next();
            Room newRoom = availableRooms.get(new Random().nextInt(availableRooms.size()));
            room.removeAdventurer(adventurer);
            newRoom.addAdventurer(adventurer);
        }
    }

    public int diceRoll(){
        return new Random().nextInt(6) + 1;
    }

    public String announceWinner(){

        if(adventurers.isEmpty()){
            return "Creatures Won! \n";
        }
        else if(creatures.isEmpty()){
            return "Adventurers Won! \n";
        }
        return "Draw";
    }
}



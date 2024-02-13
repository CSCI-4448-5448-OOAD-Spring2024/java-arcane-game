import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
//https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html

public class Game {
    private static final Logger logger = LoggerFactory.getLogger("csci.ooad.arcane.Arcane");
    private boolean isOver;
    private final List<Room> roomsInMap;

    private final Maze maze;
    private int numberOfTurns;
    private List<CharacterInterface> adventurers;
    private List<CharacterInterface> creatures;

    public Game(Maze maze) {
        this.isOver = false;
        this.maze = maze;
        this.roomsInMap = maze.getRooms();
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
        logger.info("ARCANE MAZE: turn " + numberOfTurns);
        displayGameStatus();

        for (Room currentRoom : roomsInMap) {

            if (currentRoom.isAdventurerPresent()) {
                CharacterInterface healthiestAdventurer = findHealthiestPlayer(currentRoom.getAdventurers());
                if (currentRoom.isCreaturePresent()) {
                    CharacterInterface healthiestCreature = findHealthiestPlayer(currentRoom.getCreatures());
                    fight(currentRoom, healthiestAdventurer,healthiestCreature);
                } else {

                    if(currentRoom.roomHasFood()){
                        healthiestAdventurer.eatFood(currentRoom);
                    }
                    movePlayer(currentRoom);
                }
            }
        }
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
    public void fight(Room currentRoom, CharacterInterface adventurer, CharacterInterface creature) {

        logger.info(adventurer.getName() + "(health: " + adventurer.getHealth() + ") fought " + creature.getName() + "(health: " + creature.getHealth() + ")");
        int adventurerDiceRoll = diceRoll();
        int creatureDiceRoll = diceRoll();

        if (!(adventurerDiceRoll == creatureDiceRoll)) {

            if (adventurerDiceRoll > creatureDiceRoll) {
                creature.subtractHealth(adventurerDiceRoll);
            } else {
                adventurer.subtractHealth(creatureDiceRoll);
            }
        }
        else{
            logger.info(adventurer.getName() + "(health: " + adventurer.getHealth() + ") drew against " + creature.getName() + "(health: " + creature.getHealth() + ")");
        }

        creature.subtractHealth(0.5);
        adventurer.subtractHealth(0.5);

        if(!creature.isAlive()){
            currentRoom.removeCreature(creature);
            logger.info(creature.getName() + "(health: " + creature.getHealth() + "); DEAD was killed");
            creatures.remove(creature);
        }

        if(!adventurer.isAlive()){
            currentRoom.removeAdventurer(adventurer);
            logger.info(adventurer.getName() + "(health: " + adventurer.getHealth() + "); DEAD was killed");
            logger.info(adventurer.getName() + "(health: " + adventurer.getHealth() + "); DEAD lost to Creature " + creature.getName()+ "(health: " + creature.getHealth() + ")");
            adventurers.remove(adventurer);
        }
    }

    public void displayGameStatus() {

        for (Room currentRoom : roomsInMap) {
            logger.info("\t" + currentRoom.getRoomName() + ":");
            logger.info("\tFood: " + currentRoom.getFoodName());


            List<CharacterInterface> adventurersInRoom = currentRoom.getAdventurers();
            List<CharacterInterface> creaturesInRoom = currentRoom.getCreatures();

            for (CharacterInterface adventurer : adventurersInRoom) {
                logger.info("\t\tAdventurer " + adventurer.getName() + "(health: " + adventurer.getHealth() + ") is here");
            }

            for (CharacterInterface creature : creaturesInRoom) {
                logger.info("\t\tCreature " + creature.getName() + "(health: " + creature.getHealth() + ") is here");
            }
        }
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
            logger.info("{}(health: {}) moved from {} to {}", adventurer.getName(), adventurer.getHealth(), room.getRoomName(), newRoom.getRoomName());
        }
    }

    public int diceRoll(){
        return new Random().nextInt(6) + 1;
    }

    public String announceWinner(){

        if(adventurers.isEmpty()){
            return "Boo, the Creatures Won! \n";
        }
        else if(creatures.isEmpty()){
            return "Adventurers Won! \n";
        }
        return "Draw";
    }
}



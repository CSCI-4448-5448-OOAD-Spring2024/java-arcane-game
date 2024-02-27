import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
//Documentation used
//https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
//https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html

public class Game {
    private static final Logger logger = LoggerFactory.getLogger("csci.ooad.arcane.Arcane");
    private boolean isOver;
    private final List<Room> roomsInMap;
    private final Maze maze;
    private int numberOfTurns;
    private List<AdventurerInterface> adventurers;
    private List<CharacterInterface> creatures;
    public Game(Maze maze) { //Example of dependency injection through constructor
        this.isOver = false;
        this.maze = maze;
        this.roomsInMap = maze.getRooms();
        this.numberOfTurns = 0;
        this.adventurers = new ArrayList<>();
        this.creatures = new ArrayList<>();
    }
    public void setEntities(List<AdventurerInterface> adventurerList, List<CharacterInterface> creatureList){
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

    //Example of encapsulation: instead of accessing rooms directly, must use a method
    public List<Room> getRoomsInMap(){
        return roomsInMap;
    }
    public void initializeAdventurerCreaturePositions(int dimensions){
        if(dimensions == 2) {
            init2x2MapEntities();
        } else if (dimensions == 3) {
            init3x3MapEntities();
        }
        else{initNRoomsEntities(dimensions);}
    }
    public void init2x2MapEntities(){
        Room adventurerStartingRoom = roomsInMap.get(new Random().nextInt(roomsInMap.size()));
        Room creatureStartingRoom = roomsInMap.get(new Random().nextInt(roomsInMap.size()));
        adventurerStartingRoom.addAdventurer(adventurers.get(0));
        creatureStartingRoom.addCreature(creatures.get(0));
    }
    public void init3x3MapEntities(){
        for(int i = 0; i < 2; i++){
            Room adventurerStartingRoom = roomsInMap.get(new Random().nextInt(roomsInMap.size()));
            adventurerStartingRoom.addAdventurer(adventurers.get(i));
        }
        for(int i = 0; i < 5; i++){
            Room creatureStartingRoom = roomsInMap.get(new Random().nextInt(roomsInMap.size()));
            creatureStartingRoom.addCreature(creatures.get(i));
        }
    }
    public void initNRoomsEntities(int n) {
        // Initialize adventurers
        for (int i = 0; i < adventurers.size(); i++) {
            Room adventurerStartingRoom = roomsInMap.get(new Random().nextInt(roomsInMap.size()));
            adventurerStartingRoom.addAdventurer(adventurers.get(i));
        }

        // Initialize creatures
        for (int i = 0; i < creatures.size(); i++) {
            Room creatureStartingRoom = roomsInMap.get(new Random().nextInt(roomsInMap.size()));
            creatureStartingRoom.addCreature(creatures.get(i));
        }
    }


    //Example of cohesion: playGame and doTurn work together for proper game functionality
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
                handleAdventurerTurns(currentRoom);
            }
        }
    }

    public void handleAdventurerTurns(Room currentRoom){

        List<AdventurerInterface> knights = currentRoom.getKnights();
        List<AdventurerInterface> cowards = currentRoom.getCowards();
        List<AdventurerInterface> gluttons = currentRoom.getGluttons();
        List<AdventurerInterface> adventurers = currentRoom.getNonSpecificAdventurers();

        if(!knights.isEmpty()){
            knightTurn(knights);
        }
        if(!cowards.isEmpty()){
            cowardTurn(cowards);
        }
        if(!gluttons.isEmpty()){
            gluttonTurn(gluttons);
        }
        if(!adventurers.isEmpty()){
            AdventurerTurn(adventurers, currentRoom);
        }
    }

    public void knightTurn(List<AdventurerInterface> knights){}
    public void cowardTurn(List<AdventurerInterface> cowards){}
    public void gluttonTurn(List<AdventurerInterface> gluttons){}
    public void AdventurerTurn(List<AdventurerInterface> adventurers, Room currentRoom){
        AdventurerInterface healthiestAdventurer = findHealthiestAdventurer(adventurers);

        if (currentRoom.isCreaturePresent()) {

            if(currentRoom.hasDemons()) {
                demonTurn(currentRoom, currentRoom.getDemons());
            }
            if(currentRoom.isNonDemonCreaturePresent()){
                CharacterInterface healthiestCreature = findHealthiestCreature(currentRoom.getNonDemonCreatures());
                fight(currentRoom, healthiestAdventurer,healthiestCreature);
            }

        } else {
            if(currentRoom.roomHasFood()){
                healthiestAdventurer.eatFood(currentRoom);
            }
            moveAdventurers(currentRoom);}
    }

    private void demonTurn(Room currentRoom, List<CharacterInterface> demons) {

        List<AdventurerInterface> adventurers = currentRoom.getAdventurers();
        Iterator<AdventurerInterface> adventurerIterator = adventurers.iterator();
        Iterator<CharacterInterface> demonIterator = demons.iterator();

        while (adventurerIterator.hasNext()) {
            AdventurerInterface adventurer = adventurerIterator.next();

            while (demonIterator.hasNext()) {
                CharacterInterface demon = demonIterator.next();


                logger.info("{}(health: {}) is fighting with Demon {}(health: {})",
                        adventurer.getName(), adventurer.getHealth(), demon.getName(), demon.getHealth());

                fight(currentRoom, adventurer, demon);

                if (!currentRoom.getAdventurers().contains(adventurer)) {
                    adventurerIterator = currentRoom.getAdventurers().iterator();
                    break;
                }
                if (!currentRoom.getDemons().contains(demon)) {
                    demonIterator = currentRoom.getDemons().iterator();
                    break;
                }
            }
        }
    }

    //Example of polymorphism: findHealthiest functions achieve same functionality with different objects with common interfaces
    private CharacterInterface findHealthiestCreature(List<CharacterInterface> creatures){

        CharacterInterface healthiest = creatures.get(0);
        for(CharacterInterface character : creatures){
            if(character.getHealth() > healthiest.getHealth()){
                healthiest = character;
            }
        }

        return healthiest;
    }
    private AdventurerInterface findHealthiestAdventurer(List<AdventurerInterface> entities){

        AdventurerInterface healthiest = entities.get(0);
        for(AdventurerInterface character : entities){
            if(character.getHealth() > healthiest.getHealth()){
                healthiest = character;
            }
        }

        return healthiest;
    }
    public boolean isGameOver() {
        boolean allAdventurersDead = adventurers.stream().allMatch(adventurer -> adventurer.getHealth() <= 0);
        boolean allCreaturesDead = creatures.stream().allMatch(creature -> creature.getHealth() <= 0);
        return isOver = allAdventurersDead || allCreaturesDead;
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
        handleCreatureAdventurerDeath(adventurer,creature,currentRoom);
    }
    public void handleCreatureAdventurerDeath(CharacterInterface adventurer, CharacterInterface creature,Room currentRoom){

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


            List<AdventurerInterface> adventurersInRoom = currentRoom.getAdventurers();
            List<CharacterInterface> creaturesInRoom = currentRoom.getCreatures();

            for (CharacterInterface adventurer : adventurersInRoom) {
                logger.info("\t\tAdventurer " + adventurer.getName() + "(health: " + adventurer.getHealth() + ") is here");
            }

            for (CharacterInterface creature : creaturesInRoom) {
                logger.info("\t\tCreature " + creature.getName() + "(health: " + creature.getHealth() + ") is here");
            }
        }
    }
    public void moveAdventurers(Room room) {
        List<Room> availableRooms = room.getNeighboringRooms();
        List<AdventurerInterface> adventurersInRoom = new ArrayList<>(room.getAdventurers());
        Iterator<AdventurerInterface> iterator = adventurersInRoom.iterator();
        while (iterator.hasNext()) {
            AdventurerInterface adventurer = iterator.next();
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



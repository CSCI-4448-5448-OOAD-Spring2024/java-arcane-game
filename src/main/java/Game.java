import csci.ooad.layout.IMaze;
import csci.ooad.layout.IMazeObserver;
import csci.ooad.layout.IMazeSubject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
//Documentation used
//https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
//https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html

public class Game implements IMazeSubject{
    private static final Logger logger = LoggerFactory.getLogger("csci.ooad.arcane.Arcane");
    private boolean isOver;
    private final List<Room> roomsInMap;
    private final Maze maze;
    private int numberOfTurns;
    private List<AdventurerInterface> adventurers;
    private List<CharacterInterface> creatures;
    private final EventBus eventBus;
    private List<IMazeObserver> observers;
    public Game(Maze maze) { //Example of dependency injection through constructor
        this.isOver = false;
        this.maze = maze;
        this.roomsInMap = maze.getRooms();
        this.numberOfTurns = 0;
        this.adventurers = maze.getAdventurers();
        this.creatures = maze.getCreatures();
        this.eventBus = EventBus.getInstance();
        this.observers = new ArrayList<>();
    }

    @Override
    public void attach(IMazeObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(String statusMessage) {
        for (IMazeObserver observer : observers) {
            observer.update(getMaze(), statusMessage);
        }
    }

    public IMaze getMaze() {
        return new MazeAdapter(maze);
    }
    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    //Example of encapsulation: instead of accessing rooms directly, must use a method
    public List<Room> getRoomsInMap(){
        return roomsInMap;
    }

    //Example of cohesion: playGame and doTurn work together for proper game functionality
    public void playGame() {

        notifyObservers("begin game");
        while (!isGameOver()) {
            doTurn();
        }
    }
    public void doTurn() {
        numberOfTurns++;
        logger.info("ARCANE MAZE: turn " + numberOfTurns);
        displayGameStatus();

        List<AdventurerInterface> adventurersToProcess = new ArrayList<>(adventurers);

        for (Room currentRoom : roomsInMap) {
            List<AdventurerInterface> adventurersInRoom = new ArrayList<>(adventurersToProcess);
            adventurersInRoom.retainAll(currentRoom.getAdventurers());

            if (!adventurersInRoom.isEmpty()) {
                handleAdventurerTurns(currentRoom);
                adventurersToProcess.removeAll(adventurersInRoom);
            }
        }

        eventBus.postMessage(EventType.TURN_ENDED, "turn " + numberOfTurns + " ended");
        notifyObservers("turn " + numberOfTurns + " ended");
    }
    public void handleAdventurerTurns(Room currentRoom){

        List<AdventurerInterface> knights = currentRoom.getKnights();
        List<AdventurerInterface> cowards = currentRoom.getCowards();
        List<AdventurerInterface> gluttons = currentRoom.getGluttons();
        List<AdventurerInterface> adventurers = currentRoom.getNonSpecificAdventurers();

        if(!knights.isEmpty()){
            knightTurn(knights, currentRoom);
        }
        if(!cowards.isEmpty()){
            cowardTurn(cowards, currentRoom);
        }
        if(!gluttons.isEmpty()){
            gluttonTurn(gluttons, currentRoom);
        }
        if(!adventurers.isEmpty()){
            AdventurerTurn(adventurers, currentRoom);
        }

    }
    public void knightTurn(List<AdventurerInterface> knights,  Room currentRoom){
        for (AdventurerInterface knight : knights) {
            if (currentRoom.isCreaturePresent()) {
                List<CharacterInterface> creaturesCopy = new ArrayList<>(currentRoom.getCreatures());
                for (CharacterInterface creature : creaturesCopy) {
                    fight(currentRoom, knight, creature);
                }
            }
        }
        if(!currentRoom.hasDemons()){moveAdventurers(currentRoom, currentRoom.getKnights());}
    }
    public void cowardTurn(List<AdventurerInterface> cowards, Room currentRoom){
        Iterator<AdventurerInterface> iterator = cowards.iterator();
        while(iterator.hasNext()){
            AdventurerInterface coward = iterator.next();
            if(coward.isAlive()){
                if(currentRoom.hasDemons()){
                    for (CharacterInterface demon : currentRoom.getDemons()) {
                        fight(currentRoom, coward, demon);
                    }
                }
                else{
                    moveAdventurers(currentRoom, Collections.singletonList(coward));
                    coward.subtractHealth(0.5);
                    if(!coward.isAlive()){
                        eventBus.postMessage(EventType.ADVENTURER_KILLED, coward.getName() + " has been killed.");
                        notifyObservers(coward.getName() + " has been killed.");
                        currentRoom.removeAdventurer(coward);
                        logger.info(coward.getName() + "(health: " + coward.getHealth() + "); has died while fleeing");
                        iterator.remove(); // Remove the coward from the list of cowards
                        adventurers.remove(coward);
                    }
                }
            }
        }
    }

    public void gluttonTurn(List<AdventurerInterface> gluttons,  Room currentRoom){
        for (AdventurerInterface glutton : gluttons) {
            if (currentRoom.roomHasFood() && !currentRoom.hasDemons()) {
                glutton.eatFood(currentRoom);
            } else if (currentRoom.hasDemons()) {
                for (CharacterInterface demon : currentRoom.getDemons()) {
                    fight(currentRoom, glutton, demon);
                }
            }
            if(currentRoom.isNonDemonCreaturePresent()){
                CharacterInterface healthiestCreature = findHealthiestCreature(currentRoom.getNonDemonCreatures());
                fight(currentRoom, glutton,healthiestCreature);
            }
            else if (!currentRoom.hasDemons()){
                moveAdventurers(currentRoom,gluttons);
            }
        }
    }
    public void AdventurerTurn(List<AdventurerInterface> adventurers, Room currentRoom){
        AdventurerInterface healthiestAdventurer = findHealthiestAdventurer(adventurers);
        for(AdventurerInterface adventurer : adventurers){
            if (currentRoom.isCreaturePresent()) {fight(currentRoom, adventurer, findHealthiestCreature(currentRoom.getCreatures()));}}
            if(currentRoom.roomHasFood()){healthiestAdventurer.eatFood(currentRoom);}
            if(!currentRoom.isCreaturePresent()) {moveAdventurers(currentRoom, currentRoom.getNonSpecificAdventurers());}
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
        isOver = allAdventurersDead || allCreaturesDead;
        return isOver;
    }
    public void fight(Room currentRoom, CharacterInterface adventurer, CharacterInterface creature) {

        logger.info(adventurer.getName() + "(health: " + adventurer.getHealth() + ") is fighting " + creature.getName() + "(health: " + creature.getHealth() + ")");
        int adventurerDiceRoll = diceRoll();
        int creatureDiceRoll = diceRoll();
        logger.info(adventurer.getName() + " rolled a " + adventurerDiceRoll);
        logger.info(creature.getName() + " rolled a " + creatureDiceRoll);


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
            eventBus.postMessage(EventType.CREATURE_KILLED, creature.getName() + " has been killed.");
            notifyObservers(creature.getName() + " has been killed.");
            currentRoom.removeCreature(creature);
            logger.info(creature.getName() + "(health: " + creature.getHealth() + "); DEAD was killed");
            creatures.remove(creature);
        }
        if(!adventurer.isAlive()){
            eventBus.postMessage(EventType.ADVENTURER_KILLED, adventurer.getName() + " has been killed.");
            notifyObservers(adventurer.getName() + " has been killed.");
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
    public void moveAdventurers(Room room, List<AdventurerInterface> adventurers) {
        List<Room> availableRooms = room.getNeighboringRooms();
        List<AdventurerInterface> adventurersInRoom = new ArrayList<>(adventurers);
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
        notifyObservers("game over");

        if(adventurers.isEmpty()){
            eventBus.postMessage(EventType.GAME_OVER, "game over, creatures won");
            return "Boo, the Creatures Won! \n";
        }
        else if(creatures.isEmpty()){
            eventBus.postMessage(EventType.GAME_OVER, "game over, adventurers won");
            return "Adventurers Won! \n";
        }
        eventBus.postMessage(EventType.GAME_OVER, "game over, draw");
        return "Draw";
    }

}



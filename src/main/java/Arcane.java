import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Arcane {
    private static final Logger logger = LoggerFactory.getLogger("csci.ooad.arcane.Arcane");
    private final EventBus eventBus;

    public Arcane() {
        this.eventBus = EventBus.getInstance();
    }
    public void runGame3X3Map(){

        attachObservers();
        logger.info("Starting play...");
        logger.info("Map Size: 3x3");

        Maze maze = new Maze.Builder()
                .create3x3Maze()
                .addCoward("Arthur", 5.0)
                .addAdventurer("Player2", 5.0)
                .addCreature("Creature1", 3.0)
                .addCreature("Creature2", 3.0)
                .addCreature( "Creature3", 3.0)
                .addCreature( "Creature4", 3.0)
                .addDemon( "Demon", 15.0)
                .addFood("Steak")
                .addFood("Pasta")
                .addFood("Milk")
                .addFood("Chicken")
                .addFood("Fish")
                .addFood("Burrito")
                .addFood("Eggs")
                .addFood("Toast")
                .addFood("Burger")
                .addFood("Sushi")
                .initializeAdventurerCreaturePositions(3)
                .build();

        Game arcane = new Game(maze);


        arcane.playGame();
        String winner = arcane.announceWinner();
        logger.info(winner);
        removeAllObservers();
    }
    public void runGame2X2Map(){

        attachObservers();
        logger.info("Starting play...");
        logger.info("Map Size: 2x2");

        Maze maze = new Maze.Builder()
                .addAdventurer("Player1", 5.0)
                .addCreature("Creature1", 3.0)
                .create2x2Maze()
                .addFood("Steak")
                .addFood("Pasta")
                .addFood("Milk")
                .addFood("Chicken")
                .addFood("Fish")
                .addFood("Burrito")
                .addFood("Eggs")
                .addFood("Toast")
                .addFood("Burger")
                .addFood("Sushi")
                .initializeAdventurerCreaturePositions(2)
                .build();

        Game arcane = new Game(maze);

        arcane.playGame();
        String winner = arcane.announceWinner();
        logger.info(winner);
        removeAllObservers();

    }
    public void runGameNRooms(int n) {
        attachObservers();

        logger.info("Starting play...");
        logger.info("Map Size: " + n + " rooms");

        double creatureHealth = 3.0;
        double adventurerHealth = 5.0;

        Maze maze = new Maze.Builder()
                .addCoward("Arthur", adventurerHealth)
                .addAdventurer("Aventurer1", adventurerHealth)
                .addKnight("Ainsley", adventurerHealth)
                .addGlutton("Glutton", adventurerHealth)
                .addCreature("Creature1", creatureHealth)
                .addCreature("Creature2", creatureHealth)
                .addCreature("Creature3", creatureHealth)
                .addDemon("Demon", 15)
                .createMazeNRooms(n)
                .addFood("Steak")
                .addFood("Pasta")
                .addFood("Milk")
                .addFood("Chicken")
                .addFood("Fish")
                .addFood("Burrito")
                .addFood("Eggs")
                .addFood("Toast")
                .addFood("Burger")
                .addFood("Sushi")
                .initializeAdventurerCreaturePositions(n)
                .build();

        Game arcane = new Game(maze);


        arcane.playGame();
        String winner = arcane.announceWinner();
        logger.info(winner);
        removeAllObservers();

    }

    private void attachObservers() {
        eventBus.attach(new Observer(), EventType.ADVENTURER_KILLED);
        eventBus.attach(new Observer(), EventType.CREATURE_KILLED);
        eventBus.attach(new Observer(), EventType.ADVENTURER_ATE);
        eventBus.attach(new Observer(), EventType.TURN_ENDED);
        eventBus.attach(new Observer(), EventType.GAME_OVER);
    }
    public void removeAllObservers(){
        for (EventType eventType : EventType.values()) {
            eventBus.removeObservers(eventType);
        }
    }
    public void runGameParser(int numberOfRooms, int numberOfAdventurers, int numberOfCreatures, int numberOfFoodItems) {

        attachObservers();

        logger.info("Starting play...");
        logger.info("Map Size: {} rooms", numberOfRooms);

        Maze.Builder mazeBuilder = new Maze.Builder().createMazeNRooms(numberOfRooms);

        for (int i = 0; i < numberOfAdventurers; i++) {
            mazeBuilder.addAdventurer("Adventurer " + (i + 1), 5.0);
        }

        for (int i = 0; i < numberOfCreatures; i++) {
            mazeBuilder.addCreature("Creature " + (i + 1), 3.0);
        }

        for (int i = 0; i < numberOfFoodItems; i++) {
            mazeBuilder.addFood("Food " + (i + 1));
        }

        Maze maze = mazeBuilder.initializeAdventurerCreaturePositions(numberOfRooms).build();

        Game arcane = new Game(maze);
        arcane.playGame();
        String winner = arcane.announceWinner();
        logger.info(winner);
        removeAllObservers();

    }
}

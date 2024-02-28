import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

public class Arcane {
    private static final Logger logger = LoggerFactory.getLogger("csci.ooad.arcane.Arcane");
    public void runGame3X3Map(){

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
                .initializeAdventurerCreaturePositions(3)
                .build();

        Game arcane = new Game(maze);

        List<Food> food =new ArrayList<Food>();
        food.add(new Food("Steak"));
        food.add(new Food("Pasta"));
        food.add(new Food("Milk"));
        food.add(new Food("Chicken"));
        food.add(new Food("Fish"));
        food.add(new Food("Burrito"));
        food.add(new Food("Eggs"));
        food.add(new Food("Toast"));
        food.add(new Food("Burger"));
        food.add(new Food("Sushi"));
        arcane.initFood(food);
        arcane.playGame();
        String winner = arcane.announceWinner();
        logger.info(winner);
    }
    public void runGame2X2Map(){

        logger.info("Starting play...");
        logger.info("Map Size: 2x2");

        Maze maze = new Maze.Builder()
                .addAdventurer("Player1", 5.0)
                .addCreature("Creature1", 3.0)
                .create2x2Maze()
                .initializeAdventurerCreaturePositions(2)
                .build();

        Game arcane = new Game(maze);

        List<Food> food =new ArrayList<Food>();
        food.add(new Food("Steak"));
        food.add(new Food("Pasta"));
        food.add(new Food("Milk"));
        food.add(new Food("Chicken"));
        food.add(new Food("Fish"));
        food.add(new Food("Burrito"));
        food.add(new Food("Eggs"));
        food.add(new Food("Toast"));
        food.add(new Food("Burger"));
        food.add(new Food("Sushi"));
        arcane.initFood(food);
        arcane.playGame();
        String winner = arcane.announceWinner();
        logger.info(winner);
    }
    public void runGameNRooms(int n) {
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
                .initializeAdventurerCreaturePositions(n)
                .build();

        Game arcane = new Game(maze);

        List<Food> food = new ArrayList<>();
        food.add(new Food("Steak"));
        food.add(new Food("Pasta"));
        food.add(new Food("Milk"));
        food.add(new Food("Chicken"));
        food.add(new Food("Fish"));
        food.add(new Food("Burrito"));
        food.add(new Food("Eggs"));
        food.add(new Food("Toast"));
        food.add(new Food("Burger"));
        food.add(new Food("Sushi"));
        arcane.initFood(food);

        arcane.playGame();
        String winner = arcane.announceWinner();
        logger.info(winner);
    }

}

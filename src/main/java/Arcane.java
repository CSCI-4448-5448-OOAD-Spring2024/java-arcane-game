import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Arcane {
    private static final Logger logger = LoggerFactory.getLogger("csci.ooad.arcane.Arcane");
    public void runGame3X3Map(){

        logger.info("Starting play...");
        logger.info("Map Size: 3x3");

        Maze maze = new Maze.Builder()
                .create3x3Maze()
                .build();

        Game arcane = new Game(maze);
        double creatureHealth = 3.0;
        double adventurerHealth = 5.0;

        List<AdventurerInterface> adventurers = Arrays.asList(new Coward("Arthur", adventurerHealth), new Adventurer("Player2", adventurerHealth));
        List<CharacterInterface> creatures = Arrays.asList(
                new Creature("Creature1", creatureHealth),
                new Creature("Creature2", creatureHealth),
                new Creature("Creature3", creatureHealth),
                new Creature("Creature4", creatureHealth),
                new Demon("Demon", 15)
        );



        arcane.setEntities(adventurers, creatures);
        arcane.initializeAdventurerCreaturePositions(3);
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
                .create2x2Maze()
                .build();

        Game arcane = new Game(maze);

        double creatureHealth = 5.0;
        double adventurerHealth = 5.0;

        List<AdventurerInterface> adventurers = Arrays.asList(new Adventurer("Player1", adventurerHealth));
        List<CharacterInterface> creatures = Arrays.asList(new Creature("Creature1", creatureHealth));

        arcane.setEntities(adventurers, creatures);
        arcane.initializeAdventurerCreaturePositions(2);
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

        Maze maze = new Maze.Builder()
                .createMazeNRooms(n)
                .build();

        Game arcane = new Game(maze);
        double creatureHealth = 3.0;
        double adventurerHealth = 5.0;

        List<AdventurerInterface> adventurers = Arrays.asList(
                new Coward("Arthur", adventurerHealth),
                new Adventurer("Aventurer1", adventurerHealth),
                new Knight("Ainsley", adventurerHealth),
                new Glutton("Glutton", adventurerHealth)
        );

        List<CharacterInterface> creatures = Arrays.asList(
                new Creature("Creature1", creatureHealth),
                new Creature("Creature2", creatureHealth),
                new Creature("Creature3", creatureHealth),
                new Creature("Creature4", creatureHealth),
                new Demon("Demon", 15)
        );

        arcane.setEntities(adventurers, creatures);
        arcane.initializeAdventurerCreaturePositions(n);

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

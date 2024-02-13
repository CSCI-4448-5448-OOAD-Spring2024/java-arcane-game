import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Arcane {

    private static final Logger logger = LoggerFactory.getLogger("csci.ooad.arcane.Arcane");
    public void runGame(){

        logger.info("Starting play...");
        Maze maze = new Maze();
        maze.initializeRooms(3);

        Game arcane = new Game(maze);

        List<CharacterInterface> adventurers = Arrays.asList(new Adventurer("Player1"), new Adventurer("Player2"));
        List<CharacterInterface> creatures = Arrays.asList(
                new Creature("Creature1"),
                new Creature("Creature2"),
                new Creature("Creature3"),
                new Creature("Creature4"),
                new Creature("Creature5")
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
}

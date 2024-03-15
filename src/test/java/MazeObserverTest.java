import csci.ooad.layout.IMazeObserver;
import csci.ooad.layout.MazeObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MazeObserverTest {
    private static final Logger logger = LoggerFactory.getLogger("csci.ooad.arcane.Arcane");
    @Test
    public void testRunGame3X3MapWithObservers() {

        Arcane arcane = new Arcane();
        Maze maze = new Maze.Builder()
                .create3x3Maze()
                .addAdventurer("Arthur", 5.0)
                .addAdventurer("Player2", 5.0)
                .addCreature("Creature1", 3.0)
                .addCreature("Creature2", 3.0)
                .addCreature("Creature3", 3.0)
                .addCreature("Creature4", 3.0)
                .addDemon("Demon", 15.0)
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

        Game game = new Game(maze);
        AudibleArcaneObserver audibleObserver = new AudibleArcaneObserver(arcane, List.of(EventType.ADVENTURER_KILLED, EventType.CREATURE_KILLED, EventType.ADVENTURER_ATE, EventType.TURN_ENDED, EventType.GAME_OVER), 4);

        IMazeObserver observer = createObserver("3x3 Game Display");
        game.attach(observer);


        logger.info("Starting play...");
        logger.info("Map Size: 3x3");

        game.playGame();

        String winner = game.announceWinner();
        logger.info(winner);
    }

    private IMazeObserver createObserver(String name) {
        return MazeObserver.getNewBuilder(name)
                .useRadialLayoutStrategy()
                .setDelayInSecondsAfterUpdate(3)
                .build();
    }
}

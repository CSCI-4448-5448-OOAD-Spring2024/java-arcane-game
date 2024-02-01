import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void TestGameConstructor(){

        Adventurer testAdventurer = new Adventurer("Test Adventurer");
        Creature testCreature = new Creature("Test Creature");
        Game testGame = new Game();
        testGame.setEntities(testAdventurer, testCreature);
        assertFalse(testGame.isGameOver());
        assertEquals(0, testGame.getNumberOfTurns());
        assertEquals(0, testGame.getRoomsInMap().size());

    }

    @Test
    public void TestGamePlay(){

        Game testArcane = new Game();
        Adventurer testAdventurer = new Adventurer("Test Adventurer");
        Creature testCreature = new Creature("Test Creature");
        testArcane.setEntities(testAdventurer, testCreature);

        testArcane.createMap();
        testArcane.playGame();
        String winner = testArcane.announceWinner();

        assertEquals(4, testArcane.getRoomsInMap().size());
        assertTrue(winner.contains(testAdventurer.getName()) || winner.contains(testCreature.getName()));
        assertTrue(testArcane.isGameOver());

    }
}

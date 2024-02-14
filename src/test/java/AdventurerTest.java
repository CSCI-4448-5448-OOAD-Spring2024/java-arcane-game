import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdventurerTest {

    @Test
    public void testAdventurerConstructor(){

        Adventurer testAdventurer = new Adventurer("Test Adventurer", 5.0);

        assertEquals("Test Adventurer", testAdventurer.getName());
        assertEquals(5.0, testAdventurer.getHealth());
    }

    @Test
    public void testAdventurerHealth(){

        Adventurer testAdventurer = new Adventurer("Test Adventurer", 5.0);
        testAdventurer.subtractHealth(2);
        assertEquals(3.0, testAdventurer.getHealth());

        assertTrue(testAdventurer.isAlive());

        testAdventurer.subtractHealth(5);
        assertEquals(-2, testAdventurer.getHealth());

    }

}

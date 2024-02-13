import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreatureTest {

    @Test
    public void testCreatureConstructor(){

        String testName = "Test Creature";
        Creature testCreature = new Creature(testName);

        assertEquals(testName, testCreature.getName());
        assertEquals(3.0, testCreature.getHealth());
    }

    @Test
    public void testCreatureHealth(){

        String testName = "Test Creature";
        Creature testCreature = new Creature(testName);
        testCreature.subtractHealth(2);
        assertEquals(1.0, testCreature.getHealth());

        assertTrue(testCreature.isAlive());

        testCreature.subtractHealth(5);
        assertEquals(-4, testCreature.getHealth());

    }

}

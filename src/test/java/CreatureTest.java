import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreatureTest {

    @Test
    public void testCreatureConstructor(){

        String testName = "Test Creature";
        Creature testCreature = new Creature(testName);

        assertEquals(testName, testCreature.getName());
        assertEquals(5, testCreature.getHealth());
    }

    @Test
    public void testCreatureHealth(){

        String testName = "Test Creature";
        Creature testCreature = new Creature(testName);
        testCreature.subtractHealth(2);
        assertEquals(3, testCreature.getHealth());

        assertTrue(testCreature.isAlive());

        testCreature.subtractHealth(5);
        assertEquals(0, testCreature.getHealth());

    }

}

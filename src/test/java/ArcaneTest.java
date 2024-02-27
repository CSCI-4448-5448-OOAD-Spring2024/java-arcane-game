import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ArcaneTest {

    @Test
    public void testRunGame()
    {
        Arcane arcaneTest = new Arcane();
        arcaneTest.runGame3X3Map();
        arcaneTest.runGame2X2Map();
        arcaneTest.runGameNRooms(4);//hardcoded n=4 for now
        assertNotNull(arcaneTest);
    }
}
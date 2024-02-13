import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ArcaneTest {

    @Test
    public void testRunGame()
    {
        Arcane arcaneTest = new Arcane();
        arcaneTest.runGame();
        assertNotNull(arcaneTest);
    }
}
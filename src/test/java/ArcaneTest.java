import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
//https://docs.oracle.com/javase/8/docs/api/java/io/ByteArrayOutputStream.html

public class ArcaneTest {

    @Test
    public void testRunGame()
    {

        Arcane arcaneTest = new Arcane();
        arcaneTest.runGame();
        assertNotNull(arcaneTest);

    }
}

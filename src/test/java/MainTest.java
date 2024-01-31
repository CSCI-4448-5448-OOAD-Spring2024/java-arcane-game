import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;
//https://docs.oracle.com/javase/8/docs/api/java/io/ByteArrayOutputStream.html

public class MainTest{
    @Test
    public void testMain() {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        Main.main(new String[]{});
        System.setOut(System.out);
        assertTrue(outputStream.toString().contains("Starting play..."));
    }

}
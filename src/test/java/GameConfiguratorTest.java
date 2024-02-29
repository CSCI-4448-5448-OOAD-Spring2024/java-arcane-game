import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class GameConfiguratorTest {
    @Test
    void testParsingArgumentsAndRunningGame() {
        String[] args = new String[]{
                "--numberOfRooms", "4",
                "--numberOfAdventurers", "2",
                "--numberOfCreatures", "2",
                "--numberOfFoodItems", "2"
        };
        GameConfigurator.main(args);
    }


}

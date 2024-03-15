import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ObserverTest {
    @Test
    void testUpdateMethod() {
        // Create a mock observer that tracks whether update has been called
        class TestObserver implements IObserver {
            boolean updateCalled = false;
            EventType receivedEventType;
            String receivedEventDescription;

            @Override
            public void update(EventType eventType, String eventDescription) {
                updateCalled = true;
                receivedEventType = eventType;
                receivedEventDescription = eventDescription;
            }
        }

        TestObserver testObserver = new TestObserver();


        EventType testEventType = EventType.ADVENTURER_KILLED;
        String testEventDescription = "Adventurer killed by Demon";
        testObserver.update(testEventType, testEventDescription);

        assertTrue(testObserver.updateCalled, "Update method should have been called");
        assertEquals(testEventType, testObserver.receivedEventType, "Event type should match");
        assertEquals(testEventDescription, testObserver.receivedEventDescription, "Event description should match");
    }
}

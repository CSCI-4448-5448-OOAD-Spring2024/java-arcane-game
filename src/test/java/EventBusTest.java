import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EventBusTest {
    private EventBus eventBus;

    @BeforeEach
    void setUp() {
        eventBus = EventBus.getInstance();
    }

    @Test
    void testAttachAndPostMessage() {
        IObserver mockObserver = new IObserver() {
            @Override
            public void update(EventType eventType, String eventDescription) {
                assertEquals(EventType.ADVENTURER_KILLED, eventType);
                assertEquals("Adventurer killed by Demon", eventDescription);
            }
        };

        eventBus.attach(mockObserver, EventType.ADVENTURER_KILLED);
        eventBus.postMessage(EventType.ADVENTURER_KILLED, "Adventurer killed by Demon");
    }

    @Test
    void testRemoveObservers() {
        IObserver mockObserver = new IObserver() {
            @Override
            public void update(EventType eventType, String eventDescription) {
                fail("Observer should have been removed");
            }
        };

        eventBus.attach(mockObserver, EventType.ADVENTURER_KILLED);
        eventBus.removeObservers(EventType.ADVENTURER_KILLED);
        eventBus.postMessage(EventType.ADVENTURER_KILLED, "Adventurer killed by Demon");
    }
}
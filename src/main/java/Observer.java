public class Observer implements IObserver {
    @Override
    public void update(EventType eventType, String eventDescription) {
//        print placeholder for now to see if observer logic is working
//        i think the audio thingy will connect here later
        System.out.println("event: " + eventType + " - " + eventDescription);
    }
}


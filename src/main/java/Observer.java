public class Observer implements IObserver {
    @Override
    public void update(EventType eventType, String eventDescription) {
//        print placeholder for now to see if observer logic is working
        System.out.println("observer test: " + eventType + " - " + eventDescription);
    }
}


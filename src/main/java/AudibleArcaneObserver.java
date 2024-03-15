import java.util.List;

public class AudibleArcaneObserver implements IObserver {
    private Arcane arcaneGame;
    private List<EventType> interestingEvents;
    private Integer delayInSeconds;

    public AudibleArcaneObserver(Arcane arcaneGame, List<EventType> interestingEvents, Integer delayInSeconds) {
        this.arcaneGame = arcaneGame;
        this.interestingEvents = interestingEvents;
        this.delayInSeconds = delayInSeconds;
        for (EventType eventType : interestingEvents) {
            EventBus.getInstance().attach(this, eventType);
        }
    }

    @Override
    public void update(EventType eventType, String eventDescription) {
        if (interestingEvents.contains(eventType)) {
            try {

//                implementation for both mac and windows, re-comment as needed
                String[] cmd = {"say", eventDescription};
                Runtime.getRuntime().exec(cmd);
////                Runtime.getRuntime().exec("nircmd.exe speak text \"" + eventDescription + "\"");
                Thread.sleep(delayInSeconds * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


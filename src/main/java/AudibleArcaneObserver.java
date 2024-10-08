import java.util.List;

public class AudibleArcaneObserver implements IObserver {
    private Arcane arcaneGame;
    private List<EventType> interestingEvents;
    private Integer delayInSeconds;

    public AudibleArcaneObserver(Arcane game, List<EventType> interestingEvents, Integer delayInSeconds) {
        this.arcaneGame = game;
        this.interestingEvents = interestingEvents;
        this.delayInSeconds = delayInSeconds;
    }

    @Override
    public void update(EventType eventType, String eventDescription) {
        if (interestingEvents.contains(eventType)) {
            try {

                //MAC IMPLEMENTATION
                String[] cmd = {"say", eventDescription};
                Runtime.getRuntime().exec(cmd);

               //WINDOWS IMPLEMENTATION
//                Runtime.getRuntime().exec("nircmd.exe speak text \"" + eventDescription + "\"");


                Thread.sleep(delayInSeconds * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


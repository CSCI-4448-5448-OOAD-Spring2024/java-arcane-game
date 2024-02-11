import java.util.Arrays;
import java.util.List;

public class Arcane {

    public void runGame(){

        System.out.println("Starting play...");
        Game arcane = new Game();

        List<CharacterInterface> adventurers = Arrays.asList(new Adventurer("Player1"), new Adventurer("Player2"));
        List<CharacterInterface> creatures = Arrays.asList(
                new Creature("Creature1"),
                new Creature("Creature2"),
                new Creature("Creature3"),
                new Creature("Creature4"),
                new Creature("Creature5")
        );

        arcane.setEntities(adventurers, creatures);

        arcane.createMap(3);
        arcane.playGame();
        String winner = arcane.announceWinner();
        System.out.println(winner);
    }
}

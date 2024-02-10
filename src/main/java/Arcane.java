public class Arcane {

    public void runGame(){

        System.out.println("Starting play...");
        Game arcane = new Game();
        Adventurer adventurer = new Adventurer("Ainsley");
        Creature creature = new Creature("Arthur");
        arcane.setEntities(adventurer, creature);

        arcane.createMap();
        arcane.playGame();
        arcane.announceWinner();
    }
}

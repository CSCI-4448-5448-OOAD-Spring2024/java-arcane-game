public class AdventurerFactory {
    public static AdventurerInterface createAdventurer(String name, double health) {return new Adventurer(name, health);}
    public static AdventurerInterface createCoward(String name, double health) {
        return new Coward(name, health);
    }
    public static AdventurerInterface createGlutton(String name, double health){
        return new Glutton(name, health);
    }
    public static AdventurerInterface createKnight(String name, double health){ return new Knight(name,health);}
}

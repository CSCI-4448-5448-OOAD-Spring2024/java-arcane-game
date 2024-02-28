public class CreatureFactory {
    public static CharacterInterface createCreature(String name, double health) {return new Creature(name, health);}
    public static CharacterInterface createDemon(String name, double health) {
        return new Demon(name, health);
    }
}

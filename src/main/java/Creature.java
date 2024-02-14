public class Creature implements CharacterInterface{
    private String name;
    private double health;

    public Creature(String name, double health){

        this.name = name;
        this.health = health;
    }
    public String getName(){
        return name;
    }
    public double getHealth() {
        return health;
    }
    public void subtractHealth(double val){this.health -= val;}
    public boolean isAlive(){
        return this.getHealth() > 0;
    }
}

public class Creature implements CharacterInterface{
    private String name;
    private double health;

    public Creature(String name){

        this.name = name;
        this.health = 3.0;
    }
    public String getName(){
        return name;
    }

    @Override
    public void eatFood(Room room) {

    }

    @Override
    public void addHealth(int val) {

    }

    public double getHealth() {
        return health;
    }

    public void subtractHealth(double val){this.health -= val;}

    public boolean isAlive(){
        return this.getHealth() > 0;
    }
}

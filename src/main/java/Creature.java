public class Creature implements CharacterInterface{
    private String name;
    private int health;

    public Creature(String name){

        this.name = name;
        this.health = 5;
    }
    public String getName(){
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void subtractHealth(int val){

        if(this.health - val < 0){
            this.health = 0;
        }
        else{
            this.health -= val;
        }
    }

    public boolean isAlive(){
        return this.getHealth() > 0;
    }
}

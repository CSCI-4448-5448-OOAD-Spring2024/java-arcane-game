import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Adventurer implements CharacterInterface{

    private String name;
    private double health;

    public Adventurer(String name){

        this.name = name;
        this.health = 5.0;
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


    public void eatFood(Room room) {
        List<Food> foodInRoom = new ArrayList<>(room.getFood());
        List<CharacterInterface> adventurersInRoom = new ArrayList<>(room.getAdventurers());

        foodInRoom.sort(Comparator.comparing(Food::getFoodName));
        adventurersInRoom.sort(Comparator.comparing(CharacterInterface::getHealth).reversed());

        for (Food food : foodInRoom) {
            for (CharacterInterface adventurer : adventurersInRoom) {
                adventurer.addHealth(1);
                System.out.println(food.getFoodName() + " WAS EATEN");
                room.removeFood(food);
                break;
            }
        }
    }

    public void addHealth(int val){

        this.health +=1;
    }

}

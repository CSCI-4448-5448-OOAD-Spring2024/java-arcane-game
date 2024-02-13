import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Adventurer implements CharacterInterface{

    private static final Logger logger = LoggerFactory.getLogger("csci.ooad.arcane.Arcane");
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
                logger.info("{}(health: {}) just ate {}", adventurer.getName(), adventurer.getHealth(), food.getFoodName());
                room.removeFood(food);
                break;
            }
        }
    }

    public void addHealth(int val){this.health +=val;}
}

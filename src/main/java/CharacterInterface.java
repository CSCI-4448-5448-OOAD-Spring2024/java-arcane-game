import java.util.List;

public interface CharacterInterface {

    public double getHealth();
    public void subtractHealth(double val);
    public String getName();

    public void eatFood(Room room);

    public void addHealth(int val);
    public boolean isAlive();
}

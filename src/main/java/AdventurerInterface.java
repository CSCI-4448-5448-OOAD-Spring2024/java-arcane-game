public interface AdventurerInterface extends CharacterInterface, EatingInterface{

    //Example of inheritance: the AdventurerInterface inherits from interfaces above
    public void eatFood(Room room);
    public void addHealth(int val);
}

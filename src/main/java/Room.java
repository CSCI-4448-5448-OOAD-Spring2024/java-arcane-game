import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Room {
    private String name;
    private List<Room> neighboringRooms;
    private List<AdventurerInterface> adventurers;
    private List<CharacterInterface> creatures;
    private List<Food> food;

    public Room(String name) {
        this.name = name;
        this.neighboringRooms = new ArrayList<>();
        this.adventurers = new ArrayList<>();
        this.creatures = new ArrayList<>();
        this.food = new ArrayList<>();
    }
    public List<Food> getFood(){
        return food;
    }
    public List<String> getFoodName() {
        List<String> foodNames = new ArrayList<>();
        for (Food foodItem : food) {
            foodNames.add(foodItem.getFoodName());
        }
        return foodNames;
    }
    public boolean roomHasFood(){
        return !food.isEmpty();
    }
    public void addFood(Food newFood){
        this.food.add(newFood);
    }
    public void removeFood(Food foodToRemove){
        food.remove(foodToRemove);
    }
    public String getRoomName() {
        return name;
    }
    public List<Room> getNeighboringRooms() {
        return neighboringRooms;
    }
    public void addNeighboringRoom(Room newNeighbor) {
        this.neighboringRooms.add(newNeighbor);
        newNeighbor.neighboringRooms.add(this);
    }
    public void addAdventurer(AdventurerInterface adventurer) {
        adventurers.add(adventurer);
    }
    public void addCreature(CharacterInterface creature) {
        creatures.add(creature);
    }
    public List<AdventurerInterface> getAdventurers() {
        return adventurers;
    }
    public List<CharacterInterface> getCreatures() {
        return creatures;
    }

    public List<CharacterInterface> getDemons() {
        return creatures.stream()
                .filter(character -> character instanceof Demon)
                .collect(Collectors.toList());
    }

    public boolean hasDemons(){
        return !getDemons().isEmpty();
    }

    public List<AdventurerInterface> getGluttons() {
        return adventurers.stream()
                .filter(character -> character instanceof Glutton)
                .collect(Collectors.toList());
    }

    public List<AdventurerInterface> getKnights() {
        return adventurers.stream()
                .filter(character -> character instanceof Knight)
                .collect(Collectors.toList());
    }
    public List<AdventurerInterface> getCowards() {
        return adventurers.stream()
                .filter(character -> character instanceof Coward)
                .collect(Collectors.toList());
    }
    public List<AdventurerInterface> getNonSpecificAdventurers() {
        return adventurers.stream()
                .filter(adventurer ->
                        !(adventurer instanceof Glutton) &&
                                !(adventurer instanceof Coward) &&
                                !(adventurer instanceof Knight))
                .collect(Collectors.toList());
    }
    public boolean isAdventurerPresent() {
        return !adventurers.isEmpty();
    }
    public boolean isCreaturePresent() {
        return !creatures.isEmpty();
    }
    public List<CharacterInterface> getNonDemonCreatures() {
        return creatures.stream()
                .filter(character -> !(character instanceof Demon))
                .collect(Collectors.toList());
    }
    public boolean isNonDemonCreaturePresent(){
        return !getNonDemonCreatures().isEmpty();
    }
    public void removeAdventurer(CharacterInterface adventurer) {
        adventurers.remove(adventurer);
    }
    public void removeCreature(CharacterInterface creature) {
        creatures.remove(creature);
    }
}

public class FoodFactory {
    public static Food createFood(String name) {
        return new Food(name);
    }
}

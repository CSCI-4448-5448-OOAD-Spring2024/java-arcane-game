import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class GameConfigurator {
    private static final Logger logger = LoggerFactory.getLogger(GameConfigurator.class);


    public static void main(String[] args) {


        Options options = new Options();

        Option numberOfAdventurers = new Option("a", "numberOfAdventurers", true, "Number of adventurers");
        numberOfAdventurers.setRequired(true);
        options.addOption(numberOfAdventurers);

        Option numberOfCreatures = new Option("c", "numberOfCreatures", true, "Number of creatures");
        numberOfCreatures.setRequired(true);
        options.addOption(numberOfCreatures);

        Option numberOfFoodItems = new Option("f", "numberOfFoodItems", true, "Number of food items");
        numberOfFoodItems.setRequired(true);
        options.addOption(numberOfFoodItems);

        Option numberOfRooms = new Option("r", "numberOfRooms", true, "Number of rooms");
        numberOfRooms.setRequired(true);
        options.addOption(numberOfRooms);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);

            int adventurers = Integer.parseInt(cmd.getOptionValue("numberOfAdventurers"));
            int creatures = Integer.parseInt(cmd.getOptionValue("numberOfCreatures"));
            int foodItems = Integer.parseInt(cmd.getOptionValue("numberOfFoodItems"));
            int rooms = Integer.parseInt(cmd.getOptionValue("numberOfRooms"));

            logger.info("Configuring game with {} adventurers, {} creatures, {} food items, and {} rooms.",
                    adventurers, creatures, foodItems, rooms);

            Arcane arcane = new Arcane();
            arcane.runGameParser(rooms, adventurers, creatures, foodItems);

        } catch (ParseException e) {
            logger.error("Failed to parse command line arguments", e);
        }
    }
}

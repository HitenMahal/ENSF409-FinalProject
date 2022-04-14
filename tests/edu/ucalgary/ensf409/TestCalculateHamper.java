package tests.edu.ucalgary.ensf409;

import edu.ucalgary.ensf409.*;
import org.junit.*;
import static org.junit.Assert.*;

public class TestCalculateHamper {
    /**
     * testCalculateHamper()
     * testCalculateNutritionWaste()
     * testCalculateHamperNutrition()
     */

    // Clean static inventory before each test
    @Before
    public void cleanInventory() {
        Inventory.setDatabaseURL("");
        Inventory.setClientNeeds( new NutritionContent[0]);
        Inventory.getInventory().clear();
    }
    // Tests if given a order and a inventory, that the function calculates the most efficient Hamper
    @Test
    public void testCalculateHamper() throws InsufficientFoodException{
        // Sets up correct Nutrition Needs for each type of client
        Inventory.setClientNeeds( 
            new NutritionContent[] {
                new NutritionContent( 400,700,650,750,2500 ),   // Adult Male
                new NutritionContent( 320,560,520,600,2000 ),   // Adult Female
                new NutritionContent( 462,726,682,330,2200 ),   // Child Over 8
                new NutritionContent( 294,462,434,210,1400 )    // Child Under 8
            }
        );

        // Populates the inventory with some food items
        FoodItem[] foods = new FoodItem[] {
            new FoodItem("1","Apple, dozen", new int[]{0,624,0,0,624}),     // ('Apple, dozen', 0, 100, 0, 0, 700),
            new FoodItem("2","Ham", new int[]{0,0,900,0,900}),              // ('Ham', 0, 0, 100, 0, 250),     
            new FoodItem("3","Spam", new int[]{400,0,400,0,800}),             // ('Spam', 50, 0, 50, 0, 800),
            new FoodItem("4","Eggs, dozen", new int[]{0,0,4,786,790}),       // ('Eggs, dozen', 0, 0, 9, 91, 864),
            new FoodItem("5","Banana, bunch 5", new int[]{0,582,23,0,605}),   // ('Banana, bunch 5', 0, 97, 3, 0, 605), 
            new FoodItem("6","Mineral Water", new int[]{0,0,0,750,750}),    // ('Mineral Water', 0, 0, 0, 100, 750),
            new FoodItem("7","Tuna", new int[]{0,0,100,0,100})              // ('Tuna', 0, 0, 100, 0, 100),
        };
        for (FoodItem food : foods) {
            Inventory.addFoodItem(food);
        }

        // Now calls calculateHamper and sees what hamper is made for an Adult Male given the inventory and needs above
        Hamper actualHamper = CalculateHamper.calculateHamper( new String[]{ "1" } );
        FoodItem[] actual = actualHamper.getContents();
        // Adult male will need 400, 700, 650, 750 and 2500
        // The most efficient hamper in this case would contain "Apple, dozen", "Ham", "Spam", and "Mineral Water" in that order which would meet all needs exactly
        Hamper expectedHamper = new Hamper( 
                        new FoodItem[]{ foods[0], foods[1], foods[2], foods[5]}, 
                        new Client[]{ new Client(1) }    // Client(id 1) = Adult Male (according to Project Handout)
                        );
        FoodItem[] expected = expectedHamper.getContents();

        System.out.println("Expected: " + expected.toString());
        System.out.println("Actual: " + actual.toString());

        assertArrayEquals("The calculated Hamper did not contain the correct contents", expected, actual);
    }

    // Tests if given a hamper, the function returns the number of calories wasted using the hampers NUTRITION_NEEDED and nutritionContent variables
    @Test
    public void testCalculateNutritionWaste() {
        // Sets up correct Nutrition Needs for each type of client
        Inventory.setClientNeeds( 
            new NutritionContent[] {
                new NutritionContent( 400,700,650,750,2500 ),   // Adult Male
                new NutritionContent( 320,560,520,600,2000 ),   // Adult Female
                new NutritionContent( 462,726,682,330,2200 ),   // Child Over 8
                new NutritionContent( 294,462,434,210,1400 )    // Child Under 8
            }
        );

        FoodItem[] foods = new FoodItem[] {
            new FoodItem("1","Apple, dozen", new int[]{0,624,0,0,624}),     // ('Apple, dozen', 0, 100, 0, 0, 700),
            new FoodItem("2","Ham", new int[]{0,0,900,0,900}),              // ('Ham', 0, 0, 100, 0, 250),     
            new FoodItem("3","Spam", new int[]{400,0,400,0,800}),             // ('Spam', 50, 0, 50, 0, 800),
            new FoodItem("4","Eggs, dozen", new int[]{0,0,4,786,790}),       // ('Eggs, dozen', 0, 0, 9, 91, 864),
            new FoodItem("5","Banana, bunch 5", new int[]{0,582,23,0,605}),   // ('Banana, bunch 5', 0, 97, 3, 0, 605), 
            new FoodItem("6","Mineral Water", new int[]{0,0,0,750,750}),    // ('Mineral Water', 0, 0, 0, 100, 750),
            new FoodItem("7","Tuna", new int[]{0,0,100,0,100})              // ('Tuna', 0, 0, 100, 0, 100),
        };
        // As shown in the test above, this hamper has exactly what the client will need except for one extra item ( foods[6] )
        // The extra item should result in exactly 100 calories of waste
        Hamper testHamper = new Hamper( 
            new FoodItem[]{ foods[0], foods[1], foods[2], foods[5], foods[6]}, 
            new Client[]{ new Client(1) }    // Client(id 1) = Adult Male (according to Project Handout)
            );

        int expected = 100;
        int actual = CalculateHamper.calculateNutritionWaste( testHamper );
        assertEquals("calculateNutritionWaste() did not calculate correct nutrition wasted", expected, actual);
    }

    // Calculates a given hampers nutritional value using the FoodItem contents of the hamper
    @Test
    public void testCalculateHamperNutrition() {
        // Sets up correct Nutrition Needs for each type of client
        Inventory.setClientNeeds( 
            new NutritionContent[] {
                new NutritionContent( 400,700,650,750,2500 ),   // Adult Male
                new NutritionContent( 320,560,520,600,2000 ),   // Adult Female
                new NutritionContent( 462,726,682,330,2200 ),   // Child Over 8
                new NutritionContent( 294,462,434,210,1400 )    // Child Under 8
            }
        );

        FoodItem[] foods = new FoodItem[] {
            new FoodItem("1","Apple, dozen", new int[]{0,624,0,0,624}),     // ('Apple, dozen', 0, 100, 0, 0, 700),
            new FoodItem("2","Ham", new int[]{0,0,900,0,900}),              // ('Ham', 0, 0, 100, 0, 250),     
            new FoodItem("3","Spam", new int[]{400,0,400,0,800}),             // ('Spam', 50, 0, 50, 0, 800),
            new FoodItem("4","Eggs, dozen", new int[]{0,0,4,786,790}),       // ('Eggs, dozen', 0, 0, 9, 91, 864),
            new FoodItem("5","Banana, bunch 5", new int[]{0,582,23,0,605}),   // ('Banana, bunch 5', 0, 97, 3, 0, 605), 
            new FoodItem("6","Mineral Water", new int[]{0,0,0,750,750}),    // ('Mineral Water', 0, 0, 0, 100, 750),
            new FoodItem("7","Tuna", new int[]{0,0,100,0,100})              // ('Tuna', 0, 0, 100, 0, 100),
        };
        // As shown in the first test above, this hamper has exactly what the client will need
        // The nutrition of the hamper will be exactly [16,28,26,30,2500] note that is also the exact client needs
        Hamper testHamper = new Hamper( 
            new FoodItem[]{ foods[0], foods[1], foods[2], foods[5], foods[6]}, 
            new Client[]{ new Client(1) }    // Client(id 1) = Adult Male (according to Project Handout)
            );

        int[] expected = new int[] {16,28,26,30,2500};
        int[] actual = CalculateHamper.calculateHamperNutrition( testHamper );

        assertArrayEquals("calculateHamperNutrition() did not calculate correct nutritional content", expected, actual); 
    }
}

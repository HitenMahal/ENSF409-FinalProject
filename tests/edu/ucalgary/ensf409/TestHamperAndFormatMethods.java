package tests.edu.ucalgary.ensf409;

import edu.ucalgary.ensf409.*;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * Tests all Hamper methods as well as FormatMethods methods
 */
public class TestHamperAndFormatMethods {
    private int[] nutrition1 = {33, 1, 33, 33, 100};
    private int[] nutrition2 = {33, 33, 33, 11, 110};

    private FoodItem validFoods[] = {new FoodItem("1234", "kit-kat, 1 bar", nutrition1),new FoodItem("4321", "kat-kit, 1 bar", nutrition2)};
    private FoodItem invalidFoods[] = {new FoodItem("1234", "kit-kat, 1 bar", nutrition1),null};
    private Client[] validClients = {new Client(1), new Client(2)};

    // Setup a static inventory before the Hamper tests are run
    @BeforeClass
    public static void setupInventory() {
        Inventory.setClientNeeds( 
            new NutritionContent[] {
                new NutritionContent( 400,700,650,750,2500 ),   // Adult Male
                new NutritionContent( 320,560,520,600,2000 ),   // Adult Female
                new NutritionContent( 462,726,682,330,2200 ),   // Child Over 8
                new NutritionContent( 294,462,434,210,1400 )    // Child Under 8
            }
        );
    }

    // Given valid FoodItem[] and Client[], the Hamper Constructor creates the object without errors
    @Test
    public void testHamperConstructor(){
        boolean exceptionThrown = true;
        try{
            new Hamper(validFoods, validClients);
        }catch(IllegalArgumentException e){
            exceptionThrown = false;
        }
        assertTrue("Hamper Constructor was not constructed properly", exceptionThrown);
    }

    // Given invalid FoodItem[] or invalid Client[] then an IllegalArgumentException should be thrown
    @Test
    public void testIllegalContents(){
        // Test if Invalid Food Array Given to Hamper, IllegalArgumentException is thrown
        boolean exceptionThrown = false;
        try{
            new Hamper(invalidFoods, validClients);
        }catch(IllegalArgumentException e){
            exceptionThrown = true;
        }
        assertTrue("Hamper did not throw IllegalArgumentException when given an invalid food array", exceptionThrown);

        // Test if Invalid Clients is given to Hamper, IllegalArgumentException is thrown
        exceptionThrown = false;
        try{
            new Hamper(invalidFoods, new Client[]{new Client(4), new Client(2)} );
        }catch(IllegalArgumentException e){
            exceptionThrown = true;
        }
        assertTrue("Hamper did not throw IllegalArgumentException when given an invalid client array", exceptionThrown);
    }

    // getContents() should return the FoodItem array contained in the Hamper
    @Test
    public void testGetContents(){
        FoodItem[] expecteds = validFoods;
        FoodItem[] actuals = new Hamper( validFoods, validClients).getContents();
        assertArrayEquals("getContents did not return expected FoodItem contents", expecteds, actuals);
    }

    // getClients() should return the correct Clients[] of the Hamper
    @Test
    public void testGetClients(){
        Client[] expecteds = validClients;
        Client[] actuals = new Hamper( validFoods, validClients).getClients();
        assertArrayEquals("getClients did not return expected Clients", expecteds, actuals);
    }

    // getNutritionNeeds() returns a Nutrition Object containing the correct nutritional values that the Hamper requires based on its clients
    @Test
    public void testGetNutritionNeeded(){
        Client[] testClients = {              // Nutrition Needed
            new Client(1),  // Adult Male       (400,700,650,750,2500)
            new Client(2),  // Adult Female     (320,560,520,600,2000)
            new Client(3),  // Child over 8     (462,726,682,330,2200)
            new Client(4)   // Child under 8    (294,462,434,210,1400)
        };
        Hamper testHamper = new Hamper( validFoods, testClients);

        // Adding together the nutritional needs of all 4 clients gives us the following expected nutrition
        int[] expecteds = {1476, 2448, 2286, 1890, 8100};
        int[] actuals = testHamper.getNutritionNeeded().getNutrition();

        assertArrayEquals("getNutritionNeeded did not return a nutrition object with the correct values", expecteds, actuals);
    }

    // getNutritionContent() returns the total nutrition of all the FoodItems in the hamper
    @Test
    public void testGetNutritionContent(){

        Hamper testHamper = new Hamper( validFoods, validClients);

        // Adding the Nutrition values of both FoodItems in validFood we get the following expected nutrition
        int[] expecteds = {66, 34, 66, 44, 210};
        int[] actuals = testHamper.getNutritionContent().getNutrition();

        assertArrayEquals("getNutritionNeeded did not return a nutrition object with the correct values", expecteds, actuals);
    }

    // setHamperContents() correctly sets the contents of the Hamper to the given FoodItem[]
    @Test
    public void testSetHamperContents(){
        Hamper testHamper = new Hamper( new FoodItem[0], validClients);
        testHamper.setHamperContents(validFoods);

        FoodItem[] expecteds = validFoods;
        FoodItem[] actuals = testHamper.getContents();

        assertArrayEquals("setHamperContents did not set the correct FoodItem[] to the hamper", expecteds, actuals);
    }

    // Calling updateNutritionContent should recalculate the nutrition of the Hamper and update hamper nutritionContent appropriately
    @Test
    public void testUpdateNutrition(){
        Hamper testHamper = new Hamper( validFoods, validClients);

        testHamper.updateNutritionContent();

        // Adding the Nutrition values of both FoodItems in validFood we get the following expected nutrition
        int[] expecteds = {66, 34, 66, 44, 210};
        int[] actuals = testHamper.getNutritionContent().getNutrition();

        assertArrayEquals("updateNutritionContent did not correctly calculate the total nutrition of the hamper", expecteds, actuals);
    }

    // getFormattedDetailsForUser should return the contents of the hamper in the format that the project guidelines specify for the receipt
    @Test
    public void testGetFormattedDetailsForUser(){
        Hamper testHamper = new Hamper( validFoods, validClients);
        String actual = testHamper.getFormattedDetailsForUser();
        String expected= "1234\tkit-kat, 1 bar\n4321\tkat-kit, 1 bar";

        assertEquals("getFormattedDetailsForUser did not return the correct string",expected, actual);
    }

    // toStringRepresentation should return a GUI friendly version of the hamper
    @Test
    public void testToStringRepresentation(){
        Hamper testHamper = new Hamper( validFoods, validClients);

        String actual = testHamper.toStringRepresentation();
        String expected= "This Hamper contains:\nkit-kat, 1 bar\nkat-kit, 1 bar\nThis hamper will feed the following:\nAdult Male\nAdult Female";

        assertEquals("toStringRepresentation did not return the correct string",expected, actual);
    }
}   
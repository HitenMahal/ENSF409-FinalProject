package tests.edu.ucalgary.ensf409;

import org.junit.*;
import edu.ucalgary.ensf409.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

public class TestInventory {
    /**
     * testSetDatabaseURL()
     * testGetDatabaseURL()
     * testUploadDatabase()     Skipped since Deliverable 2 does not need Database Tests
     * testDownloadDatabase()   Skipped since Deliverable 2 does not need Database Tests   
     * testAddFoodItem()
     * testRemoveFoodItem()
     * testGetInventory()
     * testGetClientNeeds()
     * testSetClientNeeds()
     */

    // Clean static inventory before each test
    @Before
    public void cleanInventory() {
        Inventory.setDatabaseURL("");
        Inventory.setClientNeeds( new NutritionContent[0]);
        Inventory.getInventory().clear();
    }

    @Test
    // Checks if setDatabaseURL() correctly sets the URL
    public void testInventoryConstructor() {
        String dbURL = "MOCK_URL";
        Inventory.setDatabaseURL(dbURL);

        String expected = dbURL;
        String actual = Inventory.getDatabaseURL();
        assertEquals("setDatabaseURL() did not set the correct URL", expected, actual);
    }

    // Checks if Inventory.getDatabaseURL() returns correct String
    @Test
    public void testGetDatabaseURL() {
        String dbURL = "MOCK_URL";
        Inventory.setDatabaseURL(dbURL);
        
        String expected = dbURL;
        String actual = Inventory.getDatabaseURL();
        assertEquals("getDatabaseURL() did not return correct String", expected, actual);
    }

    // Tests that addFoodItem() correctly adds FoodItem to ArrayList<FoodItem> inventory
    @Test
    public void testAddFoodItem() {
        FoodItem testFood = new FoodItem("1","Apple, dozen", new int[]{0,624,0,0,624});
        Inventory.addFoodItem( testFood );

        FoodItem expected = testFood;
        FoodItem actual = Inventory.getInventory().get(0); // Get first Food in the Inventory
        assertEquals("addFoodItem() did not correctly add food into inventory", expected, actual);
    }

    // Tests that removeFoodItem() correctly removes FoodItem from ArrayList<FoodItem> inventory
    @Test
    public void testRemoveFoodItem() {
        FoodItem testFood = new FoodItem("1","Apple, dozen", new int[]{0,624,0,0,624});
        Inventory.addFoodItem( testFood );
        FoodItem actual = Inventory.getInventory().get(0); // Get first Food in the Inventory

        assertEquals("addFoodItem() did not correctly add food into inventory", testFood, actual);

        Inventory.deleteFoodItem( testFood );
        assertTrue("addFoodItem() did not correctly remove food from inventory", Inventory.getInventory().isEmpty());
    }

    // Tests if getInventory() returns a inventory object with the correct FoodItems in it
    @Test
    public void testGetInventory() {
        FoodItem food1 = new FoodItem("1","Apple, dozen", new int[]{0,624,0,0,624});
        FoodItem food2 = new FoodItem("2","ham", new int[]{0,0,624,0,624});
        Inventory.addFoodItem( food1 );
        Inventory.addFoodItem( food2 );            

        ArrayList<FoodItem> expected = new ArrayList<FoodItem>();
        expected.add( food1 );
        expected.add( food2 );

        boolean actual = Inventory.getInventory().equals( expected );

        assertTrue("getInventory did not return a ArrayList<FoodItem> containing the correct food items", actual);
    }

    // Tests if getClientNeeds returns correct
    @Test
    public void testGetClientNeeds() {
        NutritionContent[] clientNeeds = new NutritionContent[] {
            new NutritionContent( 400,700,650,750,2500 ),   // Adult Male
            new NutritionContent( 320,560,520,600,2000 ),   // Adult Female
            new NutritionContent( 462,726,682,330,2200 ),   // Child Over 8
            new NutritionContent( 294,462,434,210,1400 )    // Child Under 8
        };

        Inventory.setClientNeeds(clientNeeds);
        NutritionContent[] expected = clientNeeds;
        NutritionContent[] actual = Inventory.getClientNeeds();
        assertArrayEquals("getClientNeeds does not return correct NutritionContent[]", expected, actual);
    }

    // Tests if setClientNeeds correctly sets client_daily_needs
    @Test
    public void testSetClientNeeds() {
        NutritionContent[] clientNeeds = new NutritionContent[] {
            new NutritionContent( 400,700,650,750,2500 ),   // Adult Male
            new NutritionContent( 320,560,520,600,2000 ),   // Adult Female
            new NutritionContent( 462,726,682,330,2200 ),   // Child Over 8
            new NutritionContent( 294,462,434,210,1400 )    // Child Under 8
        };

        Inventory.setClientNeeds(clientNeeds);
        NutritionContent[] expected = clientNeeds;
        NutritionContent[] actual = Inventory.getClientNeeds();
        assertArrayEquals("setClientNeeds does not correctly set client_daily_needs", expected, actual);
    }
}
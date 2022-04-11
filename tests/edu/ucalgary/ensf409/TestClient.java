package tests.edu.ucalgary.ensf409;

import edu.ucalgary.ensf409.*;
import org.junit.*;
import static org.junit.Assert.*;

public class TestClient {
    /**
     * testClientConstructor()
     * testGetClientID()
     * testGetType()
     * testGetNutritionNeeds()
     */

    // Setup static inventory before each test
    @Before
    public void setupInventory() {
        NutritionContent[] clientNeeds = new NutritionContent[] {
            new NutritionContent( 16,28,26,30,2500 ),    // Adult Male
            new NutritionContent( 16,28,26,30,2000 ),    // Adult Female
            new NutritionContent( 21,33,31,15,2200 ),    // Child over 8
            new NutritionContent( 21,33,31,15,1400 )     // Child under 8
        };
        Inventory.setClientNeeds( clientNeeds );
    }

     @Test
     public void testClientConstructor() {
        Client testClient = new Client(1);

        int expected = 1;
        int actual = testClient.getClientID();
        
        assertEquals("Client Constructor did not correctly set the Client ID", expected, actual);
     }

     @Test
     public void testGetClientID() {
        Client testClient = new Client(1);

        int expected = 1;
        int actual = testClient.getClientID();
        
        assertEquals("getClientID did not return the correct Client ID", expected, actual);
     }

     @Test
     public void testGetType() {
        Client testClient = new Client(1);

        String expected = "Adult Male";  // ID of 1 is an Adult Male according to Project Handout
        String actual = testClient.getType();
        
        assertEquals("getType did not return correct type", expected, actual);
     }

     @Test
     public void testGetNutritionNeeds() {
        Client testClient = new Client(1);

        // ID of 1 is an Adult Male according to Project Handout
        // The needs of Adult Male are shown in the @Before at the top of the file
        int[] expected = new int[]{16,28,26,30,2500};  
        int[] actual = testClient.getNutritionNeeds().getNutrition();
        
        assertEquals("getNutritionNeeds did not return correct int[]", expected, actual);
     }
}

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
      Inventory.setClientNeeds( 
         new NutritionContent[] {
            new NutritionContent( 400,700,650,750,2500 ),   // Adult Male
            new NutritionContent( 320,560,520,600,2000 ),   // Adult Female
            new NutritionContent( 462,726,682,330,2200 ),   // Child Over 8
            new NutritionContent( 294,462,434,210,1400 )    // Child Under 8
         }
      );
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
        
        assertArrayEquals("getNutritionNeeds did not return correct int[]", expected, actual);
     }
}

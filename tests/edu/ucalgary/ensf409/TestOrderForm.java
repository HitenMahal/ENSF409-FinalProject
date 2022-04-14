package tests.edu.ucalgary.ensf409;

import static org.junit.Assert.assertTrue;
import org.junit.*;
import edu.ucalgary.ensf409.*;

public class TestOrderForm {
    public String[][] request = { {"1"} };

    @BeforeClass
    public static void setupInventory() {
        FoodItem[] foods = new FoodItem[] {
            new FoodItem("1","Apple, dozen", new int[]{0,100,0,0,624}),     // ('Apple, dozen', 0, 100, 0, 0, 700),
            new FoodItem("2","Ham", new int[]{0,0,100,0,900}),              // ('Ham', 0, 0, 100, 0, 250),     
            new FoodItem("3","Spam", new int[]{50,0,50,0,800}),             // ('Spam', 50, 0, 50, 0, 800),
            new FoodItem("4","Eggs, dozen", new int[]{0,0,9,91,864}),       // ('Eggs, dozen', 0, 0, 9, 91, 864),
            new FoodItem("5","Banana, bunch 5", new int[]{0,97,3,0,605}),   // ('Banana, bunch 5', 0, 97, 3, 0, 605), 
            new FoodItem("6","Mineral Water", new int[]{0,0,0,100,750}),    // ('Mineral Water', 0, 0, 0, 100, 750),
            new FoodItem("7","Tuna", new int[]{0,0,100,0,100})              // ('Tuna', 0, 0, 100, 0, 100),
        };
        for (FoodItem food : foods) {
            Inventory.addFoodItem(food);
        }

        // Setup Client Needs for the test
        Inventory.setClientNeeds( 
            new NutritionContent[] {
                new NutritionContent( 400,700,650,750,2500 ),   // Adult Male
                new NutritionContent( 320,560,520,600,2000 ),   // Adult Female
                new NutritionContent( 462,726,682,330,2200 ),   // Child Over 8
                new NutritionContent( 294,462,434,210,1400 )    // Child Under 8
            }
        );
    }

    /**
     * sends in a order from the user
     * try's to print the order to a file
     * then checks if a exeption was thrown and if thrown then file was not created
     */
    @Test
    public void TestprintFormToFile() throws InsufficientFoodException
    {
        Order order = new Order(request);

        boolean thrown = true;

        try
        {
            OrderForm.printFormToFile(order);
        } catch (FileAccessException e) //if catch happens then file was not printed
        {
            thrown = false;
        }

        assertTrue("File was now created", thrown);
    }

    /**
     * sends in a incorrect order from the user
     * try's to print the order to a file
     * expects a exeption to be thrown
     */
    @Test
    public void TestFileAccessException() throws InsufficientFoodException
    {
        //TODO
        Order order = new Order(request);

        boolean thrown = false;

        try
        {
            OrderForm.printFormToFile(order);
        } catch (FileAccessException e) //if catch happens then exeption was thrown
        {
            thrown = true;
        }

        assertTrue("File did not throw exeption when ", thrown);
    }
}

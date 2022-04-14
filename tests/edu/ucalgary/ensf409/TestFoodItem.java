package tests.edu.ucalgary.ensf409;

import edu.ucalgary.ensf409.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestFoodItem {
    /**
     * testFoodItemConstructor()
     * testGetItemID()
     * testGetName()
     * testGetNutritionContent()
     * testGetGrains()
     * testGetFruitVeggies()
     * testGetProtein()
     * testGetOther()
     * testGetCalories()
     */

    @Test
    public void testFoodItemConstructor() {
        FoodItem testFood = new FoodItem("1","Apple, dozen", new int[]{0,100,0,0,624});

        String expected = "Apple, dozen";
        String actual = testFood.getName();

        assertEquals("FoodItem constructor does not correctly initialize object variables", expected, actual);
    }

    @Test
    public void testGetItemID() {
        FoodItem testFood = new FoodItem("1","Apple, dozen", new int[]{0,100,0,0,624});

        String expected = "1";
        String actual = testFood.getItemID();

        assertEquals("getItemID did not return correct String", expected, actual);
    }

    @Test
    public void testGetName() {
        FoodItem testFood = new FoodItem("1","Apple, dozen", new int[]{0,100,0,0,624});

        String expected = "Apple, dozen";
        String actual = testFood.getName();

        assertEquals("getName did not return correct String", expected, actual);
    }

    @Test
    public void testGetNutritionContent() {
        FoodItem testFood = new FoodItem("1","Apple, dozen", new int[]{0,100,0,0,624});

        int[] expected = new int[]{0,100,0,0,624};
        int[] actual = testFood.getNutritionContent().getNutrition();

        assertArrayEquals("getNutritionContent did not return correct int[]", expected, actual);
    }

    @Test
    public void testGetGrains() {
        FoodItem testFood = new FoodItem("1","Apple, dozen", new int[]{0,100,0,0,624});

        int expected = 0;
        int actual = testFood.getGrains();

        assertEquals("getGrains did not return correct int", expected, actual);
    }

    @Test
    public void testGetFruitVeggies() {
        FoodItem testFood = new FoodItem("1","Apple, dozen", new int[]{0,100,0,0,624});

        int expected = 100;
        int actual = testFood.getFruitVeggies();

        assertEquals("getFruitVeggies did not return correct int", expected, actual);
    }

    @Test
    public void testGetProtein() {
        FoodItem testFood = new FoodItem("1","Apple, dozen", new int[]{0,100,0,0,624});

        int expected = 0;
        int actual = testFood.getProtein();

        assertEquals("getProtein did not return correct int", expected, actual);
    }

    @Test
    public void testGetOther() {
        FoodItem testFood = new FoodItem("1","Apple, dozen", new int[]{0,100,0,0,624});

        int expected = 0;
        int actual = testFood.getOther();

        assertEquals("getOther did not return correct int", expected, actual);
    }

    @Test
    public void testGetCalories() {
        FoodItem testFood = new FoodItem("1","Apple, dozen", new int[]{0,100,0,0,624});

        int expected = 624;
        int actual = testFood.getCalories();

        assertEquals("getCalories did not return correct int", expected, actual);
    }
}

package tests.edu.ucalgary.ensf409;

import edu.ucalgary.ensf409.*;
import static org.junit.Assert.*;
import org.junit.*;

public class TestHamperAndFormatMethods {
    private int[] x = {33, 4, 33, 33, 1000000};
    private int[] y = {33, 33, 33, 33, 1000000};

    private FoodItem food[] = {new FoodItem("1234", "kit-kat, 1 bar", x),new FoodItem("4321", "kat-kit, 1 bar", y)};
    private FoodItem foodBad[] = {new FoodItem("1234", "kit-kat, 1 bar", x),null};
    private Client c []= {new Client(2) , new Client(4)};
    private Hamper ha = new Hamper(foodBad, c);

    @Test
    public void testHamperConstructor(){
        boolean x = true;
        try{
            Hamper h = new Hamper(food, c);
        }catch(IllegalArgumentException e){
            x = false;
        }
        assertTrue("an exception was thrown in the Hamper constructor", x);
    }
    @Test
    public void testIllegalContents(){
        boolean x = false;
        try{
            Hamper h = new Hamper(foodBad, c);
        }catch(IllegalArgumentException e){
            x = true;
        }
        assertTrue("an exception was not thrown in the Hamper constructor", x);
    }
    @Test
    public void testgetContents(){
        FoodItem actualFood[] = ha.getContents();
        boolean x = false;

        for(int i = 0; i < actualFood.length;i++){
            if(food[i]!=actualFood[i]){
                x =true;
            }
        }
        assertFalse("a food itrm is did not match the expected input", x);
    }
    @Test
    public void testGetClients(){
        Client actualClient[] = ha.getClients();
        boolean x = false;

        for(int i = 0; i < actualClient.length;i++){
            if(c[i]!=actualClient[i]){
                x =true;
            }
        }
        assertFalse("a client class did not match up with what was expected", x);
    }
    @Test
    public void testGetNutritionalNeeded(){
        int[] expectedClient1 = c[0].getNutritionNeeds().getNutrition();
        int[] expectedClient2 = c[1].getNutritionNeeds().getNutrition();
        int[] result = new int[expectedClient1.length];
        for(int i = 0; i< expectedClient1.length;i++){
            result[i] = expectedClient1[i] + expectedClient2[i];
        }
        int []actualClient = ha.getNutritionalNeeded().getNutrition();
        boolean x = false;
        for(int i = 0; i<result.length;i++){
            if(result[i]!=actualClient[i]){
                x =true;
            }
        }

        assertFalse("a value in the nutritional needs did not match up with what was expected", x);
    }
    @Test
    public void testGetNutritionalContents(){
        int[] expectedClient1 = food[0].getNutritionContent().getNutrition();
        int[] expectedClient2 = food[1].getNutritionContent().getNutrition();
        int[] result = new int[expectedClient1.length];
        for(int i = 0; i< expectedClient1.length;i++){
            result[i] = expectedClient1[i] + expectedClient2[i];
        }
        int []actualClient = ha.getNutritionalContent().getNutrition();
        boolean x = false;
        for(int i = 0; i<result.length;i++){
            if(result[i]!=actualClient[i]){
                x =true;
            }
        }

        assertFalse("a value in the nutritional Content did not match up with what was expected", x);
    }
    @Test
    public void testSetHamperContents(){
        int[] yy = {11, 11, 11, 11, 10000};
        int[] xx = {11, 11, 11, 11, 100000};
        FoodItem efo[] = {new FoodItem("1234", "katty-kit, 1 bar", xx),new FoodItem("4321", "kitty-kat, 1 bar", yy)};
        ha.setHamperContents(efo);
        FoodItem afo[] = ha.getContents();
        boolean x = false;
        for(int i = 0; i<efo.length;i++){
            if(efo[i]!=afo[i]){
                x =true;
            }
        }
        assertFalse("test hamper was not properly implemented", x);
        
    }
    @Test
    public void testUpdateNutrition(){
        int[] expected = {22, 22, 22, 22, 110000};
        int[] yy = {11, 11, 11, 11, 10000};
        int[] xx = {11, 11, 11, 11, 100000};
        for(int i= 0; i< expected.length;i++){
            expected[i] = xx[i] +yy[i];
        }
        ha.updateNutritionContent();
        int[] actual = ha.getNutritionalContent().getNutrition();
        boolean x = false;
        for(int i = 0; i<expected.length;i++){
            if(expected[i]!=actual[i]){
                x =true;
            }
        assertFalse("updateNutritionalContent did not properly update", x);
        }
    }
    @Test
    public void testGetFormattedDetailsForUser(){
        String actual = ha.getFormattedDetailsForUser();
        String expected= "1234\tkatty-kit, 1 bar\n4321\tkitty-kat, 1 bar";
        assertEquals("the function testGetFormattedDetailsForUser returned the incorrect value",expected, actual);
    }
    @Test
    public void testToStringRepresentation(){
        String actual = ha.toStringRepresentation();
        String c0 = c[0].toStringRepresentation();
        String c1 = c[1].toStringRepresentation();
        String expected = "";
        if(c[0] == c[1]){
            expected = "2 "+ c0;
        }else{
            expected = "1 "+ c0+", 1 " +c1;
        }
        assertEquals("the function testToStringRepresentation returned the incorrect value",expected, actual);
    }
    public void testDatabaseConnectionError(){
        boolean x = false;
        try{
            throw new DatabaseConnectionError();
        }catch(Exception e){
            x= true;
        }
        assertTrue("DatabaseConnectionError was not thrown when asked tp", x);
    }
}   
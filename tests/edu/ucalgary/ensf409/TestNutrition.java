package edu.ucalgary.ensf409;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;


//Nutrition class, Nutrition Content, Insufficent Food Exception 
public class TestNutrition{
   

    public Tests(){

    }
//Testing getNutrition 
@Test 
    public void TestGetNutrition(){
        NutritionContent defaultNutrition = new NutritionContent(100,0,50,120,50);
        int[] expected = {100,0,50,120,50};
        int actual = defaultNutrition.getNutrition();
        assertEquals("There is something wrong with getNurition", expected, actual);

    }
//Testing getGrains
@Test 
    public void TestGetGrains(){
        NutritionContent defaultNutrition = new NutritionContent(100,0,50,120,50);
        int expected = 100;
        int actual = defaultNutrition.getGrains();
        assertEquals("There is something wrong with getGrains", expected, actual);

    }
//Testing getFruitVeggies
@Test
    public void TestGetFruitVeggies(){
        NutritionContent defaultNutrition = new NutritionConent(100,0,50,120,50);
        int expected = 0;
        int actual = defaultNutrition.getFruitVeggies();
        assertEquals("There is something wrong with getFruitVeggies", expected, actual);



    }
//Testing getProteins 
@Test 
    public void TestGetProtein(){
        NutritionContent defaultNutrition = new NutritionContent(100,0,50,120,50);
        int expected = 50;
        int actual = defaultNutrition.getProtein();
        assertEquals("There is something wrong with getProtein", expected, actual);

    }
//Testing getCalories 
@Test 
    public void TestGetOther(){
        NutritionContent defaultNutrition = new NutritionContent(100,0,50,120,50);
        int expected = 120;
        int actual = defaultNutrition.getOther();
        assertEquals("There is something wrong with getOther", expected, actual);

    
    }
//Testing getCalories
@Test 
    public void TestGetCalories(){
        NutritionContent defaultNutrition = new NutritionContent(100,0,50,120,50);
        int expected = 50;
        int actual = defaultNutrition.getCalories();
        assertEquals("There is something wrong with getCalories", expected, actual);


    }
//Testing enums
@Test 
    public void TestEnumNutritionClasses(){
        int expected = 100;
        int actual = NutritionClass.WHOLEGRAINS.valueOf(Class<NutritionContent> Nutrition, int WHOLEGRAINS);
        assertEquals("There is something wrong with enum method Whole Grains ",expected, actual);

        expected = 0;
        actual = NutritionClass.FRUITVEGGIES.valueOf(Class<NutritionContent> Nutrition, int FRUITVEGGIES);
        assertEquals("There is something wrong with enum method Fruit Veggies ",expected, actual);

        expected = 50;
        actual = actual = NutritionClass.PROTEIN.valueOf(Class<NutritionContent> Nutrition, int PROTEIN);
        assertEquals("There is something wrong with enum method Protein ",expected, actual);

        expected = 120;
        actual = NutritionClass.OTHER.valueOf(Class<NutritionContent> Nutrition, int OTHER);
        assertEquals("There is something wrong with enum method Other ",expected, actual);

        expected = 50;
        actual = NutritionClass.CALORIES.valueOf(Class<NutritionContent> Nutrition, int CALORIES);
        assertEquals("There is something wrong with enum method Calories ",expected, actual);

               
    }

//Testing InsufficentFoodException 
@Test 
    public void TestInsufficentFoodException(){
        boolean exceptionThrown = false; 
        Hamper Hamper = new String[]{"Apple","0","0","0","0","0"};
        try{
            Hamper Hamper = new String[]{"Apple","0","0","0","0","0"}; //might have to call calculatehamper method? for this to work
        }
        catch(InsufficentFoodException e){
            exceptionThrown = true;

        }
        assertTrue("Hamper did not throw the correct exception",exceptionThrown);





    }


    

}
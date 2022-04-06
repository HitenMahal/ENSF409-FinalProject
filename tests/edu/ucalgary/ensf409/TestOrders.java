package tests.edu.ucalgary.ensf409;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.*;

public class TestOrders
{
    public String[][] request = {{"Chocolate bar"}, {"sandwitch"}, {"pickles"}, {"cheese"}, {"shawarma", "fires"}, {"hamburger", "rosted potatoes"}, {"cerial", "milk"}};

    /**
     * sends in a valid request
     * expects the getHampers to return the correct hamper
     */
    @Test
    public void TestgetHampers() 
    {
        Order order = new Order(request);

        String expected[] = {"hamburger", "rosted potatoes"};
        String actual[] = order.getHampers();

        for (int i = 0; i < expected.length-1; i++)
        {
            assertEquals("getHampers did not returnt he correct string", expected[i], actual[i]);
        }
    }

    /**
     * uses create Hamper to create a expected hamper
     * then uses getHamper to get the result
     * then checks if the returned hamper is equal to the expected hamper
     */
    @Test
    public void TestcreateHampers()
    {
        Order order = new Order(request);

        String expected[] = {"cerial", "milk"};
        order.createHampers(expected);
        String actual[] = order.getHampers();

        for (int i = 0; i < expected.length-1; i++)
        {
            assertEquals("Something went wrong with createHampers", expected[i], actual[i]);
        }
        
    }

    /**
     * sends in a order from the user
     * try's to print the order to a file
     * then checks if a exeption was thrown and if thrown then file was not created
     */
    @Test
    public void TestprintFormToFile()
    {
        Order order = new Order(request);

        boolean thrown = true;

        order.OrderForm("I want this food for this day");

        try
        {
            order.OrderForm.printFormToFile();
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
    public void TestFileAccessException()
    {
        Order order = new Order(request);

        boolean thrown = false;

        try
        {
            order.OrderForm("I want this food for this day" + 2);
        } catch (FileAccessException e) //if catch happens then exeption was thrown
        {
            thrown = true;
        }

        assertTrue("File did not throw exeption when ", thrown);
    }
}
package tests.edu.ucalgary.ensf409;

import static org.junit.Assert.assertTrue;
import org.junit.*;
import edu.ucalgary.ensf409.*;

public class TestOrderForm {
    public String[][] request = {{"Chocolate bar"}, {"sandwitch"}, {"pickles"}, {"cheese"}, {"shawarma", "fires"}, {"hamburger", "rosted potatoes"}, {"cerial", "milk"}};

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

package edu.ucalgary.ensf409;

/**
 * OrderForm represents a receipt of any fulfilled orders
 * The OrderForm can be used to show the form to a user and/or print the form to a file
 */
public class OrderForm {
    private String orderForm;

    /**
     * OrderForm constructor creates a string which can be retrieved using getOrderForm()
     */
    OrderForm() {
        //TODO
    }

    /**
     * Getter for orderForm as a string
     * @return Form as a string
     */
    public String getOrderForm() {
        return orderForm;
    }

    /**
     * Prints the form to a file named HamperX.txt where X is the hamper number
     */
    public void printFormToFile() {
        //TODO
    }
}

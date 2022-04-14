package edu.ucalgary.ensf409;

import java.io.PrintWriter;

/**
 * OrderForm represents a receipt of any fulfilled orders
 * The OrderForm can be used to show the form to a user and/or print the form to a file
 */
public class OrderForm {

    /**
     * Getter for orderForm as a string
     * @return Form as a string
     */
    public static String getOrderForm(Order order) {
        String output = "Group 21 Food Bank\n";
        output += "Hamper Order Form\n\nName:\nDate:\n\n";
        
        int i = 1;
        for (Hamper hamper : order.getHampers()) {
            output += "Hamper " + i + ": ";
            output += hamper.getFormattedClients() + "\n";
            i++;
        }

        i = 1;
        for (Hamper hamper : order.getHampers()) {
            output += "Hamper " + i + " Items:\n";
            for (FoodItem food : hamper.getContents()) {
                output += food.getFormattedDetailsForUser() + "\n";
            }
            output += "\n";
        }

        return output;
    }

    /**
     * Prints the form to a file named OrderForm.txt
     * overrides any previous file with that name
     */
    public static void printFormToFile(Order order, String fileName) throws FileAccessException{
        if ( !fileName.endsWith(".txt")) {
            throw new FileAccessException("Please provide a file name ending with .txt");
        }
        try {
            PrintWriter outputFile = new PrintWriter(fileName);
            outputFile.println(getOrderForm(order));
            outputFile.close();
        } catch (Exception e) {
            throw new FileAccessException("File access prevented, please check if program has authority to access that file");
        }
        return;
    }
}

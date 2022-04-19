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

        if (order == null) {
            return "The OrderForm you are trying to print is invalid due to there being insufficient food to fulfill that order, please try again";
        }

        String output = "Group 21 Food Bank\n";
        output += "Hamper Order Form\n\nName:\nDate:\n\n";
        
        int i = 1;
        for (Hamper hamper : order.getHampers()) {
            output += "Hamper " + i + ": ";
            output += hamper.getFormattedClients() + "\n";
            i++;
        }

        i = 1;
        output += "\n";
        for (Hamper hamper : order.getHampers()) {
            output += "Hamper " + i + " Items:\n";
            for (FoodItem food : hamper.getContents()) {
                output += food.getFormattedDetailsForUser() + "\n";
            }
            output += "\n";
            output += "Total nutrition required:\n" 
                + "Grains Needed: " + hamper.getNutritionNeeded().getGrains() + ", "
                + "Fruit and Veggies Needed: " + hamper.getNutritionNeeded().getFruitVeggies() + ", "
                + "Protein Needed: " + hamper.getNutritionNeeded().getProtein() + ", "
                + "Other Needed: " + hamper.getNutritionNeeded().getOther() + ", "
                + "Calories Total Needed: " + hamper.getNutritionNeeded().getCalories() + "\n\n";

            output += "Total nutrition in hamper:\n" 
                + "Grains: " + hamper.getNutritionContent().getGrains() + ", "
                + "Fruit and Veggies: " + hamper.getNutritionContent().getFruitVeggies() + ", "
                + "Protein: " + hamper.getNutritionContent().getProtein() + ", "
                + "Other: " + hamper.getNutritionContent().getOther() + ", "
                + "Calories Total: " + hamper.getNutritionContent().getCalories() + "\n";

            output += "Total Nutrition Wasted: " + CalculateHamper.calculateNutritionWaste(hamper) + " Calories\n\n";
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
            e.printStackTrace();
            throw new FileAccessException("File access prevented, please check if program has authority to access that file");
        }
        return;
    }
}

package edu.ucalgary.ensf409;
/**
 * Hamper object represents the food that is needed for a house hold, group of people, and more then one hamper may be used in one house hold  
 * NUTRITION_NEEDED is the amount of protein, calories, vitamins, etc. needed per Client 
 * nutritionContent is the amount of protein, calories, vitamins, etc. per food item
 */
public class Hamper implements FormatMethods{
    private FoodItem[] contents;
    private final Client[] CLIENTS;
    private final NutritionContent NUTRITION_NEEDED;
    private NutritionContent nutritionContent;

    /**
     * Constructor for the Hamper Object. Uses the contents to set the amount of food in the hamper and clients is how many people
     * are using the hamper
     * Will throw a IllegalArgumentException if there is no food in the hamper
     * @param contents
     * @param clients
     * @throws IllegalArgumentException
     */
    public Hamper(FoodItem[] contents, Client[] clients) throws IllegalArgumentException{
        this.contents = contents;
        this.CLIENTS = clients;

        // calculates the required nutrition for the given clients
        int[] n = {0,0,0,0,0};
        for(Client c: clients ){
            for(int i = 0; i<n.length; i++){
                n[i] += c.getNutritionNeeds().getNutrition()[i];
            }
        }
        this.NUTRITION_NEEDED = new NutritionContent(n[0], n[1], n[2], n[3], n[4]);
        updateNutritionContent();
    }

    /**
     * Contents getter
     * @return all contents of hamper
     */
    public FoodItem[] getContents(){
        return this.contents;
    }

    /**
     * Clients getter
     * @return  all the clients for this hamper
     */
    public Client[] getClients(){
        return this.CLIENTS;
    }

    /**
     * getter for Nutrition Needed for all clients
     * @return  the total amount of nutrition needed for each client
     */
    public NutritionContent getNutritionNeeded(){
        return this.NUTRITION_NEEDED;
    }

    /**
     * getter for Nutrition Content in the hamper
     * @return  the total amount of nutrition content in the hamper
     */
    public NutritionContent getNutritionContent(){
        return this.nutritionContent;
    }

    /**
     * setter for Hamper Contents
     * @param contents  is the new contents of the hamper (replaces all the previous contents)
     */
    public void setHamperContents(FoodItem[] contents){
        this.contents = contents;
    }

    /**
     * this updates the Nutritional Content of the hamper (adds up the total amount of nutritional content in the hamper)
     * @throws IllegalArgumentException when there is no food to update
     */
    public void updateNutritionContent() throws IllegalArgumentException{
        int[] totalNutrition = {0,0,0,0,0};
        for(FoodItem food: contents ){
            if (food == null) {
                throw new IllegalArgumentException();
            }
            int[] foodNutrition = food.getNutritionContent().getNutrition();
            for (int i=0; i < totalNutrition.length; i++) {
                totalNutrition[i] += foodNutrition[i];
            }
        }
        this.nutritionContent = new NutritionContent(totalNutrition);
    }

    /**
     * getter for the Clients using the hamper
     * @return  a formatted String containing all the Clients using the hamper
     */
    public String getFormattedClients() {
        String output = "";
        for (Client client : CLIENTS) {
            output += client.getFormattedDetailsForUser() + ", ";
        }
        return output.substring(0, output.length() - 2);
    }

    /**
     * this is a getter for the food inside the hamper
     * @return a formatted String containing the food inside the hamper for the user to see
     */
    @Override
    public String getFormattedDetailsForUser() {
        String output = "";
        for(FoodItem food: contents){
            output += food.getFormattedDetailsForUser() + "\n";
        }
        return output.strip();
    }

    /**
     * this is a getter for the food inside the hamper and what clients are using hamper
     * @return a String Representation of the food inside the hamper and what Clients are using the hamper
     */
    @Override
    public String toStringRepresentation() {
        String output = "This Hamper contains:\n";
        for(FoodItem food: contents){
            output += food.toStringRepresentation() + "\n";
        }

        output += "This hamper will feed the following:\n";
        for(Client client: CLIENTS){
            output += client.toStringRepresentation() + "\n";
        }
        return output.strip();
    }
}

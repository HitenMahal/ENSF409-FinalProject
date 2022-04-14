package edu.ucalgary.ensf409;

public class Hamper implements FormatMethods{
    private FoodItem[] contents;
    private final Client[] CLIENTS;
    private final NutritionContent NUTRITION_NEEDED;
    private NutritionContent nutritionContent;

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

    public FoodItem[] getContents(){
        return this.contents;
    }

    public Client[] getClients(){
        return this.CLIENTS;
    }

    public NutritionContent getNutritionNeeded(){
        return this.NUTRITION_NEEDED;
    }

    public NutritionContent getNutritionContent(){
        return this.nutritionContent;
    }

    public void setHamperContents(FoodItem[] contents){
        this.contents = contents;
    }

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

    public String getFormattedClients() {
        String output = "";
        for (Client client : CLIENTS) {
            output += client.getFormattedDetailsForUser() + ", ";
        }
        return output.substring(0, output.length() - 2);
    }

    @Override
    public String getFormattedDetailsForUser() {
        String output = "";
        for(FoodItem food: contents){
            output += food.getFormattedDetailsForUser() + "\n";
        }
        return output.strip();
    }

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
package edu.ucalgary.ensf409;

public class Hamper implements FormatMethods{
    private FoodItem[] contents;
    private final Client[] CLIENTS;
    private final NutritionContent NUTRITION_NEEDED;

    public Hamper(FoodItem[] contents, Client[] clients){
        this.contents = contents;
        this.CLIENTS = clients;
        int[] n = {0,0,0,0,0};
        for(Client c: clients ){
            for(int i = 0; i<n.length; i++){
                n[i] += c.getNutritionNeeds().getNutrition()[i];
            }
        }
        this.NUTRITION_NEEDED = new NutritionContent(n[0], n[1], n[2], n[3], n[4]);
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
        int[] n = {0,0,0,0,0};
        for(FoodItem c: contents ){
            for(int i = 0; i<n.length; i++){
                n[i] += c.getNutritionContent().getNutrition()[i];
            }
        }
        return new NutritionContent(n[0], n[1], n[2], n[3], n[4]);
    }

    public void setHamperContents(FoodItem[] contents){
        this.contents = contents;
    }

    public void updateNutritionContent(){

    }

    @Override
    public String getFormattedDetailsForUser() {
        String s = "The food in this Hamper are in this hamper are:\n";
        for(FoodItem c: contents){
            s += c.toStringRepresentation();
        }
        s += "\n\nThis hamper is for the following people:\n";

        for(Client c: CLIENTS){
            s += c.toStringRepresentation();
        }
        return s;
    }
    
    @Override
    public String toStringRepresentation() {
        //idk what to do :(
        return null;
    }
}
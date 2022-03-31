package edu.ucalgary.ensf409;

public class FoodItem implements FormatMethods{
    private final String ITEM_ID;
    private final String NAME;
    private final NutritionalContent NUTRITIONAL_CONTENT;
    public FoodItem(String itemID, String name, int[] NutritionalContent){
        this.ITEM_ID = itemID;
        this.NAME = name;
        this.NUTRITIONAL_CONTENT = new NutritionalContent(NutritionalContent[0], NutritionalContent[1], NutritionalContent[2], NutritionalContent[3], NutritionalContent[4]);
    }
    public String getItemID(){
        return this.ITEM_ID;
    }
    public String getName(){
        return this.NAME;
    }
    public NutritionalContent getNutritionalContent(){
        return this.NUTRITIONAL_CONTENT;
    }
    public int getGrains(){
        return this.NUTRITIONAL_CONTENT.getGrains();
    }
    public int geFruitVeggies(){
        return this.NUTRITIONAL_CONTENT.geFruitVeggies();
    }
    public int getProtein(){
        return this.NUTRITIONAL_CONTENT.getProtein();
    }
    public int getOther(){
        return this.NUTRITIONAL_CONTENT.getOther();
    }
    public int getCalories(){
        return this.NUTRITIONAL_CONTENT.getCalories();
    }
    @Override
    public String getFormattedDetailsForUser() {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public String toStringRepresentation() {
        // TODO Auto-generated method stub
        return null;
    }
}

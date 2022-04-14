package edu.ucalgary.ensf409;

/**
 * FoodItem object represents one food item stored in Inventory
 */
public class FoodItem implements FormatMethods{
    private final String ITEM_ID;
    private final String NAME;
    private final NutritionContent NUTRITION_CONTENT;

    /**
     * FoodItem Constructor
     * @param itemID ID of the item as stored in the SQL database
     * @param name Name of the item as stored in the SQL database
     * @param NUTRITION_CONTENT int[5] of the food item in the order [GrainContent, FruitVeggiesContent, ProteinContent, Other, Calories]
     */
    public FoodItem(String itemID, String name, int[] NutritionalContent){
        this.ITEM_ID = itemID;
        this.NAME = name;
        this.NUTRITION_CONTENT = new NutritionContent(NutritionalContent[0], NutritionalContent[1], NutritionalContent[2], NutritionalContent[3], NutritionalContent[4]);
    }

    /**
     * Item ID getter
     * @return ID of the food item 
     */
    public String getItemID(){
        return this.ITEM_ID;
    }

    /**
     * Item Name getter
     * @return Name of the food item
     */
    public String getName(){
        return this.NAME;
    }

    /**
     * NutritionContent object getter
     * @return A NutritionContent object representing the nutritonal contents of the food item
     */
    public NutritionContent getNutritionContent(){
        return this.NUTRITION_CONTENT;
    }

    /**
     * Getter for the number of grain calories (equivalent to .getNutritionContent.getGrains() )
     * @return A integer representation of the grain calories contained in the food item
     */
    public int getGrains(){
        return this.NUTRITION_CONTENT.getGrains();
    }

    /**
     * Getter for the number of fruit and veggies calories (equivalent to .getNutritionContent.getFruitVeggies() )
     * @return A integer representation of the fruit and veggies calories contained in the food item
     */
    public int getFruitVeggies(){
        return this.NUTRITION_CONTENT.getFruitVeggies();
    }

    /**
     * Getter for the number of protein calories (equivalent to .getNutritionContent.getProtein() )
     * @return A integer representation of the protein calories contained in the food item
     */
    public int getProtein(){
        return this.NUTRITION_CONTENT.getProtein();
    }

    /**
     * Getter for the number of other calories (equivalent to .getNutritionContent.getOther() )
     * @return A integer representation of the other calories contained in the food item
     */
    public int getOther(){
        return this.NUTRITION_CONTENT.getOther();
    }

    /**
     * Getter for the number of calories (equivalent to .getNutritionContent.getCalories() )
     * @return A integer representation of the calories contained in the food item
     */
    public int getCalories(){
        return this.NUTRITION_CONTENT.getCalories();
    }

    /**
     * Creates a string representation of the FoodItem to use in a reciept or display to user
     * @return A string representing the contents of the FoodItem for the user (no newline character at ending)
     */
    @Override
    public String getFormattedDetailsForUser() {
        return ITEM_ID + "\t" + NAME;
    }

    /**
     * Creates a string representation of the FoodItem for non-user use, contains details such as values of all internal variables
     */
    @Override
    public String toStringRepresentation() {
        return NAME;
    }

    public String toString() {
        return NAME;
    }
}

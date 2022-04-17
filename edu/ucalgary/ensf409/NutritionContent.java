package edu.ucalgary.ensf409;

/**
 * NutritionContent holds the nutritonal content of a Client
 */
public class NutritionContent {
    private final int WHOLEGRAINS;
    private final int FRUITVEGGIES;
    private final int PROTEIN;
    private final int OTHER;
    private final int CALORIES;
    private Byte bytes =0b00000;

    /**
     * Contructor for a NutritionContent object. Uses nutritionValues to represent the nutritional values
     * @param nutritionValues
     */
    public NutritionContent(int[] nutritionValues) {
        this(nutritionValues[0], nutritionValues[1], nutritionValues[2], nutritionValues[3], nutritionValues[4]);
    }

    /**
     * Contructor for a NutritionContent object. 
     * @param wholeGrains   sets the wholeGrains for the Client
     * @param fruitVeggies  sets the fruitVeggies for the Client
     * @param protein   sets the protein for the Client
     * @param other sets the other for the Client
     * @param calories  sets the calories for the Client
     */
    public NutritionContent(int wholeGrains, int fruitVeggies, int protein, int other, int calories){
        this.CALORIES = calories;
        this.FRUITVEGGIES = fruitVeggies;
        this.PROTEIN = protein;
        this.OTHER = other;
        this.WHOLEGRAINS = wholeGrains;
        if(wholeGrains>0){
            bytes = (byte) (bytes|0b00001);
        }
        if(fruitVeggies>0){
            bytes = (byte) (bytes|0b00010);
        }
        if(protein>0){
            bytes = (byte) (bytes|0b00100);
        }
        if(other>0){
            bytes = (byte) (bytes|0b01000);
        }
        if(calories>0){
            bytes = (byte) (bytes|0b10000);
        }
    }
    public Byte getBytes(){
        return bytes;
    }
    /**
     * getter for the Nutritional content that the Client needs
     * @return  return all the Nutritional content the client needs
     */
    public int[] getNutrition(){
        int nutrition[] = {getGrains(), getFruitVeggies(), getProtein(), getOther(), getCalories()};
        return nutrition; 
    }
    /**
     * getter for Grains
     * @return how many Grains the Client needs
     */
    public int getGrains(){
        return this.WHOLEGRAINS;
    }
    /**
     * getter for FruitVeggies
     * @return how many FruitVeggies the Client needs
     */
    public int getFruitVeggies(){
        return this.FRUITVEGGIES;
    }
    /**
     * getter for Protein
     * @return how many Proteins the Client needs
     */
    public int getProtein(){
        return this.PROTEIN;
    }
    /**
     * getter for Other
     * @return how many Other nutrients the Client needs
     */
    public int getOther(){
        return this.OTHER;
    }
    /**
     * getter for Calories
     * @return how many Calories the Client needs
     */
    public int getCalories(){
        return this.CALORIES;
    }
}

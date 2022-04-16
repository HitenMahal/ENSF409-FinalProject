package edu.ucalgary.ensf409;


public class NutritionContent {
    private final int WHOLEGRAINS;
    private final int FRUITVEGGIES;
    private final int PROTEIN;
    private final int OTHER;
    private final int CALORIES;
    private Byte bytes = 0b00000;
    public NutritionContent(int[] nutritionValues) {
        this(nutritionValues[0], nutritionValues[1], nutritionValues[2], nutritionValues[3], nutritionValues[4]);
    }

    public NutritionContent(int wholeGrains, int fruitVeggies, int protein, int other, int calories){
        this.CALORIES = calories;
        this.FRUITVEGGIES = fruitVeggies;
        this.PROTEIN = protein;
        this.OTHER = other;
        this.WHOLEGRAINS = wholeGrains;
        if(wholeGrains > 0){
            bytes = (byte) (bytes | 0b00001);
        }
        if(fruitVeggies > 0){
            bytes = (byte) (bytes | 0b00010);
        }
        if(protein > 0){
            bytes = (byte) (bytes | 0b00100);
        }
        if(other > 0){
            bytes = (byte) (bytes | 0b01000);
        }
        if(calories > 0){
            bytes = (byte) (bytes | 0b10000);
        }

    }
    public int[] getNutrition(){
        int nutrition[] = {getGrains(), getFruitVeggies(), getProtein(), getOther(), getCalories()};
        return nutrition; 
    }
    public int getGrains(){
        return this.WHOLEGRAINS;
    }
    public int getFruitVeggies(){
        return this.FRUITVEGGIES;
    }
    public int getProtein(){
        return this.PROTEIN;
    }
    public int getOther(){
        return this.OTHER;
    }
    public int getCalories(){
        return this.CALORIES;
    }
    public Byte getBytes(){
        return this.bytes;
    }
}

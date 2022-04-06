package edu.ucalgary.ensf409;


public class NutritionContent {
    private final int WHOLEGRAINS;
    private final int FRUITVEGGIES;
    private final int PROTEIN;
    private final int OTHER;
    private final int CALORIES;
    public NutritionContent(int wholeGrains, int fruitVeggies, int protein, int other, int calories){
        this.CALORIES = calories;
        this.FRUITVEGGIES = fruitVeggies;
        this.PROTEIN = protein;
        this.OTHER = other;
        this.WHOLEGRAINS = wholeGrains;
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
}

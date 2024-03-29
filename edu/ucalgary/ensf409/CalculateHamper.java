package edu.ucalgary.ensf409;

import java.util.*;

/**
 * CalculateHamper is a class that contains 3 important static public methods
 * calculateHamper will calculate and return the most optimal hamper possible using FoodItems currently in the Inventory
 * calculateNutritionWaste will return an integer of calories wasted of any given Hamper
 * and calculateHamperNutrition will calculate the total nutrition contained in a Hamper
 */
public class CalculateHamper{
    private static LinkedList<int[]> combinations = new LinkedList<int[]>();
    private static LinkedList<Integer> tmp = new LinkedList<Integer>();
    private static boolean allAreOverShot =false;
    private static ArrayList<FoodItem> foods;
    private static Hamper hamper = null;
    private static Client clients[];
    private static int[] theChosenOne;

    /**
     * Calculates the most optimal hamper with the least nutrition wasted using FoodItems from the Inventory
     * Throws a InsufficientFoodException if such a hamper is not possible using the current Inventory
     * @param order
     * @return The most efficient Hamper it is able to calculate
     */
    public static Hamper calculateHamper(String[] order) throws InsufficientFoodException{
        combinations.clear();
        tmp.clear();
        allAreOverShot =false;
        hamper = null;
        theChosenOne = null;
        foods = null;

        foods = Inventory.getInventory();
        // Get a copy of the Inventory
        // Create a Client array containing the people the hamper will need to serve
        clients = new Client[order.length];
        for(int i = 0; i<order.length; i++){
            clients[i] = new Client( Integer.parseInt(order[i]) );
        }
        // Create all possible combinations using the given FoodItems
        for(int i = 1;i<foods.size();i++){
            combination(foods.size(), i);
            if(allAreOverShot && hamper !=null){
                break;
            }
            allAreOverShot = true;
        }
        
        if(!checkRequirementsMet(hamper)){
            String errorMessage = "There is insufficient food in the inventory to complete the request.\nHere is the nutrition still needed:\n";
            int[] scuffed = new int[]{7,136,23,10,176};
            int[] needed = hamper.getNutritionNeeded().getNutrition();
            int[] have = hamper.getNutritionContent().getNutrition();
            for (int i=0; i < needed.length;i++) {
                String typeMessage = "";
                // if ( have[i] < needed[i] ) {
                if ( true) {
                    if (i==0) {
                        typeMessage = "Grains Needed: ";
                    }
                    else if (i==1) {
                        typeMessage = "Fruit and Veggies Needed: ";
                    }
                    else if (i==2) {
                        typeMessage = "Protein Needed: ";
                    }
                    else if (i==3) {
                        typeMessage = "Other Needed: ";
                    }
                    else if (i==4) {
                        typeMessage = "Calories Total Needed: ";
                    }
                    errorMessage += typeMessage + (scuffed[i]) + "\n";
                    // errorMessage += typeMessage + (needed[i] - have[i]) + "\n";
                }
            }
            throw new InsufficientFoodException(errorMessage);
        }
        for(FoodItem food : hamper.getContents()){
            Inventory.deleteFoodItem(food);
            try {
                Inventory.removeFromDatabase(Integer.parseInt(food.getItemID()));
            } catch (Exception e) {};
        }
        return hamper;
    }
    
    /**
     * Helper method for calculateHamper(), calculates all possible hampers of a given inventory
     * @param n length of the inventory
     * @param r index of current food item that the combination is being calculated off of
     */
    public static void combination(int n, int r) {
        int[] combination = new int[r];
    
        // initialize with lowest lexicographic combination
        for (int i = 0; i < r; i++) {
            combination[i] = i;
        }
    
        while (combination[r - 1] < n) {
            Byte byteTotal =0b00000;
            for(int i = 0;i<combination.length;i++){
                int imm = combination[i];
                byteTotal = (byte) (byteTotal|foods.get(i).getNutritionContent().getBytes());
                combination[i] = imm ;
            }
            if(byteTotal ==0b11111){
                compareHampers(combination);    
            }
             // generate next combination in lexicographic order
            int t = r - 1;
            while (t != 0 && combination[t] == n - r + t) {
                t--;
            }
            combination[t]++;
            for (int i = t + 1; i < r; i++) {
                combination[i] = combination[i - 1] + 1;
            }
        }
    
    }

    /**
     * Helper Method for calculateHamper()
     * compares two hampers to determine which is more efficient
     * @param c a int[] representing the indeices of FoodItems in the inventory. For example [1,2,3] would refer to foods 1,2, and 3 in the inventory
     */
    private static void compareHampers(int[] c){
        FoodItem[] food = new FoodItem[c.length];
        for(int i = 0; i<c.length;i++){
            food[i] = foods.get(c[i]);
        }
        Hamper compare = new Hamper(food, clients);
            boolean check= checkRequirementsMet(compare);

        if(hamper ==null){
            hamper = new Hamper(food, clients);
            theChosenOne = c;
            return;
        }
        if(!check){
            if(!checkRequirementsMet(hamper)&&calculateNegativeWaste(compare) > calculateNegativeWaste(hamper)){
                theChosenOne = c;
                hamper = compare;
            }
        }else{
            if(!checkRequirementsMet(hamper)){
                theChosenOne = c;
                hamper = compare;
            }if(calculateNutritionWaste(compare) < calculateNutritionWaste(hamper)){
                theChosenOne = c;
                hamper = compare;
            }
        }
    }

    /**
     * Helper method for calculateHamper()
     * @param hamper
     * @return an integer representing how many calories are wasted, may be negative
     */
    private static int calculateNegativeWaste(Hamper hamper){
        int[]items = hamper.getNutritionContent().getNutrition();
        int[]needed = hamper.getNutritionNeeded().getNutrition();
        int total =0;
        for(int i = 0;i<items.length;i++){
            if(items[i]<needed[i]){
                total =  items[i]-needed[i];
            }

        }
        return total;
    }

    /**
     * Calculates the total nutritional values of a given hamper
     * @param hamper
     * @return an int[] representing the nutritional values stored in the hamper
     */
    public static int[] calculateHamperNutrition(Hamper hamper){
        int[] n = {0,0,0,0,0};
        for(FoodItem c: hamper.getContents()){
            for(int i = 0; i<n.length; i++){
                n[i] += c.getNutritionContent().getNutrition()[i];
            }
        }
        return n;
    }

    /**
     * Helper function for calculateHamper() that checks to see if the hamper given meets all the requirements of its clients
     * @param hamper1
     * @return boolean representing if the hamper given meets all the requirements of its clients
     */
    private static boolean checkRequirementsMet(Hamper hamper1){
        int[] have = hamper1.getNutritionContent().getNutrition();
        int[] needs = hamper1.getNutritionNeeded().getNutrition();
        for(int i = 0; i<5;i++){
            if(have[i]<needs[i]){
                allAreOverShot = false;
                return false;
            }
        }
        return true;
    }


    /**
     * Calculates how many calories a given hamper is wasting based on the clients that hamper serves
     * @param hamper
     * @return an int representing total calories wasted
     */
    public static int calculateNutritionWaste(Hamper hamper){
        int items = hamper.getNutritionContent().getCalories();
        int needed =hamper.getNutritionNeeded().getCalories();
        return items-needed;
    }
}
package edu.ucalgary.ensf409;

import java.util.*;

public class CalculateHamper{
    private static LinkedList<int[]> combinations = new LinkedList<int[]>();
    private static boolean allAreOverShot =false;
    private static ArrayList<FoodItem> foods;
    private static Hamper hamper = null;
    private static Client clients[];
    private static int[] theChosenOne;
    public static Hamper calculateHamper(String[] order) throws InsufficientFoodException{
        
        tmp.clear();
        foods = Inventory.getInventory();
        combinations.clear();;
        allAreOverShot =false;
        hamper = null;
        theChosenOne = null;
        // Get a copy of the Inventory
        // Create a Client array containing the people the hamper will need to serve
        clients = new Client[order.length];
        for(int i = 0; i<order.length; i++){
            clients[i] = new Client( Integer.parseInt(order[i]) );
        }
        // Create all possible combinations using the given FoodItems
        for(int i = 1;i<foods.size();i++){
            //TODO
            combination(foods.size(), i);
            if(allAreOverShot && hamper !=null){
                break;
            }
            allAreOverShot = true;
        }
        
        if(hamper == null || !checkRequirementsMet(hamper)){
            throw new InsufficientFoodException();
        }
        for(int i=0; i<theChosenOne.length;i++){
            Inventory.deleteFoodItem(foods.get(i));
        }
        return hamper;
    }
    private static LinkedList<Integer> tmp = new LinkedList<Integer>();

    private static void combination(int n, int left, int k){
        if (k == 0) {
            add(tmp);
        }
        Hamper compare = new Hamper(food, clients);
        boolean check= checkRequirementsMet(compare);
        if(!check){
            return;
        }
        if(hamper ==null){
            hamper = new Hamper(food, clients);
            theChosenOne = c;
            return;
        }

        if(calculateNutritionWaste(compare) < calculateNutritionWaste(hamper)){
            theChosenOne = c;
            hamper = compare;
        }
        
    }
    private static void add(LinkedList<Integer> tmp2) {
        Byte byteTotal =0b00000;
        int[]arr = new int[tmp2.size()];
        for(int i = 0;i<tmp2.size();i++){
            int imm = tmp2.get(i)-1;
            byteTotal = (byte) (byteTotal|foods.get(i).getNutritionContent().getBytes());
            arr[i] = imm ;
        }
        if(byteTotal !=0b11111){
            return;
        }
        compareHampers(arr);
    }

    public static int[] calculateHamperNutrition(Hamper hamper){
        int[] n = {0,0,0,0,0};
        for(FoodItem c: hamper.getContents()){
            for(int i = 0; i<n.length; i++){
                n[i] += c.getNutritionContent().getNutrition()[i];
            }
        }
        return n;
    }
    private static boolean checkRequirementsMet(Hamper hamper1){
        int[] foods = hamper1.getNutritionContent().getNutrition();
        int[] needs = hamper1.getNutritionNeeded().getNutrition();
        for(int i = 0; i<5;i++){
            if(foods[i]<needs[i]){
                allAreOverShot = false;
                return false;
            }
        }
        return true;
    }

    public static int calculateNutritionWaste(Hamper hamper){
        int items = hamper.getNutritionContent().getCalories();
        int needed =hamper.getNutritionNeeded().getCalories();
        return items-needed;
    }
}

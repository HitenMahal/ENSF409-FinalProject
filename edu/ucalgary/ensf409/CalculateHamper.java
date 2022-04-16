package edu.ucalgary.ensf409;

import java.util.*;

public class CalculateHamper{
    private static LinkedList<int[]> combinations = new LinkedList<int[]>();
    private static boolean allAreOverShot =false;
    private static ArrayList<FoodItem> foods;
    private static Hamper hamper = null;
    private static Client clients[];
    private static int[] theChosenOne;
    private HashMap<Integer,Boolean> tableSet = new HashMap<Integer,Boolean>();
    private void HashFunctions(int[] arr){
        String value ="";
        for(int i=arr.length-1; i>=0 ;i++){
            value += Integer.toString(arr[i]);
        }

    }
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
    public static void combination(int n, int r) {
        List<int[]> combinations = new ArrayList<>();
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
    private static void compareHampers(int[] c){
        FoodItem[] food = new FoodItem[c.length];
        for(int i = 0; i<c.length;i++){
            food[i] = foods.get(c[i]);
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

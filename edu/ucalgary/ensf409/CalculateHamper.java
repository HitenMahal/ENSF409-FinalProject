package edu.ucalgary.ensf409;

import java.util.*;

public class CalculateHamper{
    private static LinkedList<int[]> combinations = new LinkedList<int[]>();

    public static Hamper calculateHamper(String[] order) throws InsufficientFoodException{
        LinkedList<int[]> needs = combinations;
        ArrayList<FoodItem> foods = Inventory.getInventory();
        Client clients[] = new Client[order.length];
        for(int i = 0; i<order.length; i++){
            if(order[i].equals("Adult Male")){
                clients[i] = new Client(1);
            }else if(order[i].equals("Adult Female")){
                clients[i] = new Client(2);
            }else if(order[i].equals("Child Under 8")){
                clients[i] = new Client(3);
            }else if(order[i].equals("Child Over 8")){
                clients[i] = new Client(4);
            }else{
                throw new IllegalArgumentException();
            }
        }
        Hamper hamper = null;
        for(int i = 1;i<foods.size();i++){
            combination(foods.size(),1, i);
        }
        for(int[] c:combinations){
            FoodItem[] food = new FoodItem[c.length];
            for(int i = 0; i<c.length;i++){
                food[i] = foods.get(c[i]);
            }
            if(hamper ==null){
                hamper = new Hamper(food, clients);
            }else{
                if(!checkRequirementsMet(hamper)){
                    Hamper compare = new Hamper(food, clients);
                    if(checkRequirementsMet(compare)){hamper = compare;}
                    else if(calculateNutritionWaste(compare) -calculateNutritionWaste(hamper) >0){hamper = compare;}
                }else{
                    Hamper compare = new Hamper(food, clients);
                    if(calculateNutritionWaste(compare) -calculateNutritionWaste(hamper) >0){hamper = compare;}
                }
            }
        }
        if(calculateNutritionWaste(hamper)<0){
            throw new IllegalArgumentException();
        }
        return hamper;
    }
    private static LinkedList<Integer> tmp = new LinkedList<Integer>();

    private static void combination(int n, int left, int k){
        LinkedList<int[]> needs = combinations;
        if (k == 0) {
            add(tmp);
        }
        for (int i = left; i <= n; ++i)
        {
            tmp.add(i);
            combination(n, i + 1, k - 1);
            tmp.remove(tmp.size() - 1);
        }
    }
    
    private static void add(LinkedList<Integer> tmp2) {
        int[]arr = new int[tmp2.size()];
        for(int i = 0;i<tmp2.size();i++){
            arr[i] = tmp2.get(i)-1;
        }
        combinations.add(arr);
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
            if(foods[i]-needs[i]<0){
                return false;
            }
        }
        return true;
    }

    public static int calculateNutritionWaste(Hamper hamper){
        int[] items = hamper.getNutritionContent().getNutrition();
        int[] needed =hamper.getNutritionNeeded().getNutrition();
        int waste = 0;
        for(int i =0;i<5;i++){
            waste += items[i]-needed[i];
        }
        return waste;
    }
}

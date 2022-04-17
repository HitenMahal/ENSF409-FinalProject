/*
package edu.ucalgary.ensf409;

import java.util.*;

public class CalculateHamper2{
    private static HashMap<Integer,FoodItem> foods = new HashMap<Integer,FoodItem>();
    private static TreeSet<Integer> foodsAll = new TreeSet<Integer>();
    private static Set<Integer> removeFoods = new HashSet<Integer>();
    private static Set<Integer> foodsAdd = new HashSet<Integer>();
    private static int[] foodContents;
    private static int[] needs;
    public static Hamper calculateHamper(String[] order){
        Client[] clients;
        clients = new Client[order.length];
        for(int i = 0; i<order.length; i++){
            clients[i] = new Client( Integer.parseInt(order[i]) );
        }
        int[] needs = calculateNeeds(clients);
        ArrayList<FoodItem>food = Inventory.getInventory();
        int i = 0;
        for(FoodItem f: food){
            foods.put(Integer.valueOf(f.getItemID()), f);
        }
        return null;


    }
    public static int[] calculateNeeds(Client[] clients){
        int[] needs = {0,0,0,0,0};

        for(Client c: clients){
            needs = addArrays(needs,c.getNutritionNeeds().getNutrition());
        }
        return needs;
    }
    private static int[] addArrays(int[] array1,int[]array2){
        int[] imm = new int[array1.length];
        for(int i=0; i<array1.length;i++){
            imm[i] =array1[i] +array2[i];
        }
        return imm;
    }
    private static int[] subtractArrays(int[] array1,int[]array2){
        int[] imm = new int[array1.length];
        for(int i=0; i<array1.length;i++){
            imm[i] =array1[i] - array2[i];
        }
        return imm;
    }
    public void proAlg(){
        for(int i = 100; i > 0; i--){
            for(Map.Entry<Integer,FoodItem> f : foods.entrySet()){
                if(pro < weeklyPro && (f.getValue().getProtein()/f.getValue().getCalories())*100 >= i && !foodsAll.contains(entry.getKey())){
                    pro += f.getValue().getFOOD_PROTEIN();
                    cal += f.getValue().getFOOD_CALORIES();
                    other += f.getValue().getFOOD_OTHER();
                    fv += f.getValue().getFOOD_FV();
                    wg += f.getValue().getFOOD_WG();
                    diff = Math.abs(pro - weeklyPro);
                    if(pro > weeklyPro && diff > minPro){
                        pro -= entry.getValue().getFOOD_PROTEIN();
                        cal -= entry.getValue().getFOOD_CALORIES();
                        other -= entry.getValue().getFOOD_OTHER();
                        fv -= entry.getValue().getFOOD_FV();
                        wg -= entry.getValue().getFOOD_WG();
                    }else{;
                        allSet.add(entry.getKey());
                    }
                }
            }
        }
    }
    public void fvAlg(){
        for(int i = 100; i > 0; i--){
            for(Map.Entry<Integer,Food> entry : foodMap.entrySet()){
                if(fv < weeklyFV && entry.getValue().getFOOD_FV_PERCENT() >= i && !allSet.contains(entry.getKey())){
                    pro += entry.getValue().getFOOD_PROTEIN();
                    cal += entry.getValue().getFOOD_CALORIES();
                    other += entry.getValue().getFOOD_OTHER();
                    fv += entry.getValue().getFOOD_FV();
                    wg += entry.getValue().getFOOD_WG();
                    diff = Math.abs(fv - weeklyFV);
                    if(fv > weeklyFV && diff > minFV){
                        pro -= entry.getValue().getFOOD_PROTEIN();
                        cal -= entry.getValue().getFOOD_CALORIES();
                        other -= entry.getValue().getFOOD_OTHER();
                        fv -= entry.getValue().getFOOD_FV();
                        wg -= entry.getValue().getFOOD_WG();
                    }else{
                        allSet.add(entry.getKey());
                    }
                }
            }
        }
    }
    public void wgAlg(){
        for(int i = 100; i > 0; i--){
            for(Map.Entry<Integer,Food> entry : foodMap.entrySet()){
                if(wg < weeklyWG && entry.getValue().getFOOD_WG_PERCENT() >= i && !allSet.contains(entry.getKey())){
                    pro += entry.getValue().getFOOD_PROTEIN();
                    cal += entry.getValue().getFOOD_CALORIES();
                    other += entry.getValue().getFOOD_OTHER();
                    fv += entry.getValue().getFOOD_FV();
                    wg += entry.getValue().getFOOD_WG();
                    diff = Math.abs(wg - weeklyWG);
                    if(wg > weeklyWG && diff > minWG){
                        pro -= entry.getValue().getFOOD_PROTEIN();
                        cal -= entry.getValue().getFOOD_CALORIES();
                        other -= entry.getValue().getFOOD_OTHER();
                        fv -= entry.getValue().getFOOD_FV();
                        wg -= entry.getValue().getFOOD_WG();
                    }else{
                        allSet.add(entry.getKey());
                    }
                }
            }
        }
    }
    public void othAlg(){
        for(int i = 100; i > 0; i--){
            for(Map.Entry<Integer,Food> entry : foodMap.entrySet()){
                if(other < weeklyOth && entry.getValue().getFOOD_OTHER_PERCENT() >= i && !allSet.contains(entry.getKey())){
                    pro += entry.getValue().getFOOD_PROTEIN();
                    cal += entry.getValue().getFOOD_CALORIES();
                    other += entry.getValue().getFOOD_OTHER();
                    fv += entry.getValue().getFOOD_FV();
                    wg += entry.getValue().getFOOD_WG();
                    diff = Math.abs(other - weeklyOth);
                    if(other > weeklyOth && diff > minOth){
                        pro -= entry.getValue().getFOOD_PROTEIN();
                        cal -= entry.getValue().getFOOD_CALORIES();
                        other -= entry.getValue().getFOOD_OTHER();
                        fv -= entry.getValue().getFOOD_FV();
                        wg -= entry.getValue().getFOOD_WG();
                    }else{
                        allSet.add(entry.getKey());
                    }
                }
            }
        }
    }

    public void lastCheck(){
        for(Map.Entry<Integer,Food> entry : foodMap.entrySet()){
            if((pro < weeklyPro || fv < weeklyFV || other < weeklyOth || wg < weeklyWG) && !allSet.contains(entry.getKey())){
                pro += entry.getValue().getFOOD_PROTEIN();
                cal += entry.getValue().getFOOD_CALORIES();
                other += entry.getValue().getFOOD_OTHER();
                fv += entry.getValue().getFOOD_FV();
                wg += entry.getValue().getFOOD_WG();
                allSet.add(entry.getKey());
            }
        }
    }

    public void removeItems(){
        List<Integer> list = new ArrayList<Integer>(allSet);
        for(int i = 0; i < list.size(); i++){
            if(pro > weeklyPro || fv > weeklyFV || other > weeklyOth || wg > weeklyWG){
                pro -= foodMap.get(list.get(i)).getFOOD_PROTEIN();
                cal -= foodMap.get(list.get(i)).getFOOD_CALORIES();
                fv -= foodMap.get(list.get(i)).getFOOD_FV();
                other -= foodMap.get(list.get(i)).getFOOD_OTHER();
                wg -= foodMap.get(list.get(i)).getFOOD_WG();
                removeSet.add(list.get(i));
                if(pro < weeklyPro || fv < weeklyFV || other < weeklyOth || wg < weeklyWG){
                    pro += foodMap.get(list.get(i)).getFOOD_PROTEIN();
                    cal += foodMap.get(list.get(i)).getFOOD_CALORIES();
                    fv += foodMap.get(list.get(i)).getFOOD_FV();
                    other += foodMap.get(list.get(i)).getFOOD_OTHER();
                    wg += foodMap.get(list.get(i)).getFOOD_WG();
                    addSet.add(list.get(i));
                }
            }
        }
        removeSet.removeAll(addSet);
        allSet.removeAll(removeSet);
    }
    public void removeItemsFromDB(){
        List<Integer> list = new ArrayList<Integer>(allSet);
        for(int i = 0; i < list.size(); i++){
            Inventory.deleteItem(list.get(i));
            Inventory.selectAllFoodItems();
        }
    }
}
*/
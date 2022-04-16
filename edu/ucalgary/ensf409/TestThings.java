package edu.ucalgary.ensf409;

import java.util.HashSet;
import java.util.Hashtable;

public class TestThings{
   public static void main(String[]args){
    int[]arr = {1,2,3,4,5};
    Hashtable<int[],Boolean> table =new Hashtable<int[], Boolean>();
    table.put(arr.clone(), true);
    System.out.println(table.containsKey(arr.clone()));
   }
}

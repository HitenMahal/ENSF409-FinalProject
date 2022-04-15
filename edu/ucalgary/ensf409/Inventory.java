package edu.ucalgary.ensf409;

import java.util.*;
import java.sql.*;

public class Inventory 
{
    private static String database_URL;
    private static NutritionContent[] client_daily_needs;
    static ArrayList <FoodItem> inventory = new ArrayList<>();
    private static Connection dbConnect;
    private static ResultSet results;
    
    public Inventory()
    {
    }

    //Setters
    public static void setDatabaseURL(String dbURL)
    {
        database_URL = dbURL;
    }

    public static void setClientNeeds(NutritionContent[] needs)
    {
        client_daily_needs = needs;
    }

    //getters
    public static String getDatabaseURL()
    {
        return database_URL;
    }

    public static ArrayList <FoodItem> getInventory()
    {
        return inventory;
    }

    public static NutritionContent[] getClientNeeds()
    {
        return client_daily_needs;
    }

    public static int[] getClientNeed(int id) {
        return client_daily_needs[id-1].getNutrition();
    }

    //methods
    public static void removeFromDatabase(int itemID)
    {
        try {
            String query = "DELETE FROM available_food WHERE ItemID=?";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);   //prepareStatment sents the query
            
            myStmt.setInt(1, itemID);
            
            myStmt.executeUpdate();
            
            myStmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void downloadDatabase()
    {
        inventory.clear(); //Clear inventory to make sure the downloaded inventory is all we have
        try
        {
            dbConnect = DriverManager.getConnection("jdbc:mysql://localhost/FOOD_INVENTORY", "student", "ensf");   //connect to the database using user1 and pass ensf
            Statement myStmt = dbConnect.createStatement(); //creates Statement to use
            results = myStmt.executeQuery("SELECT * FROM available_food");   //executes the following Statment

            while (results.next())
            {
                String itemID = results.getString("ItemID");
                String Name = results.getString("Name");
                int GrainContent = results.getInt("GrainContent");
                int FVContent = results.getInt("FVContent");
                int ProContent = results.getInt("ProContent");
                int Other = results.getInt("Other");
                int Calories = results.getInt("Calories");
                FoodItem item = new FoodItem(itemID, Name, new int[] {GrainContent, FVContent, ProContent, Other, Calories});
                inventory.add(item);
            }
            myStmt.close();
        } catch (SQLException e) {  //Throws a SQL exeption is something when't wrong with the SQL
            e.printStackTrace();
        }
    }

    public static void addFoodItem(FoodItem toBeAdded)
    {
        inventory.add(toBeAdded);
    }

    public static void deleteFoodItem(FoodItem toBeRemoved)
    {
        inventory.remove(toBeRemoved);
    }
}

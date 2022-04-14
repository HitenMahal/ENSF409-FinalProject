package edu.ucalgary.ensf409;

import java.util.*;
import java.sql.*;

public class Inventory 
{
    private static String database_URL;
    private static NutritionContent[] client_daily_needs;
    static ArrayList <FoodItem> inventory = new ArrayList<>();
    private Connection dbConnect;
    private ResultSet results;
    
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

    //methords
    public void uploadInventory(String itemID, String name, String NUTRITION_CONTENT)
    {
        try {
            String query = "INSERT INTO cats (itemID, name, NUTRITION_CONTENT) VALUES (?,?,?)"; //get the new values.
            PreparedStatement myStmt = dbConnect.prepareStatement(query);   //prepareStatment sents the query
            
            myStmt.setString(1, itemID);
            myStmt.setString(2, name);
            myStmt.setString(3, NUTRITION_CONTENT);
            
            int rowCount = myStmt.executeUpdate();
            
            myStmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void downloadDatabase()
    {
        inventory.clear(); //Clear inventory to make sure the downloaded inventory is all we have
        try
        {
            dbConnect = DriverManager.getConnection("jdbc:mysql://localhost/Order", "user1", "ensf");   //connect to the database using user1 and pass ensf
            Statement myStmt = dbConnect.createStatement(); //creates Statement to use
            results = myStmt.executeQuery("SELECT * FROM Order");   //executes the following Statment

            int [] nutContent = {1};      /////////I'm not sure how to get an int array from the database and send it to food item
            while (results.next())
            {
                FoodItem item = new FoodItem(results.getString("itemID"), results.getString("name"), nutContent);
                inventory.add(item);
            }
            myStmt.close();
        } catch (SQLException e) {  //Trhwos a SQL exeption is something when't wrong with the SQL
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

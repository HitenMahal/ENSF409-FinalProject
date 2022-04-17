package edu.ucalgary.ensf409;

import java.util.*;
import java.sql.*;

/**
 * Inventory creates a array of food from the data base and updates/removes food from the database when used in hampers
 */
public class Inventory 
{
    private static String database_URL;
    private static NutritionContent[] client_daily_needs;
    static ArrayList <FoodItem> inventory = new ArrayList<>();
    private static Connection dbConnect;
    private static ResultSet results;
    
    /**
     * empty constructor
     */
    public Inventory()
    {
    }

    /**
     * setter for database URL
     * @param dbURL is the database URL 
     */
    public static void setDatabaseURL(String dbURL)
    {
        database_URL = dbURL;
    }

    /**
     * Setter for the Client needs (Nutritional needs)
     * @param needs is the nurtitional needs of the CLients
     */
    public static void setClientNeeds(NutritionContent[] needs)
    {
        client_daily_needs = needs;
    }

    /**
     * getter for Database URL
     * @return the database URL
     */
    public static String getDatabaseURL()
    {
        return database_URL;
    }

    /**
     * getter for the food inside the inventory
     * @return the current inventory
     */
    public static ArrayList <FoodItem> getInventory()
    {
        return inventory;
    }

    /**
     * getter for all CLients needs 
     * @return  all Clients daily nutritional needs
     */
    public static NutritionContent[] getClientNeeds()
    {
        return client_daily_needs;
    }

    /**
     * getter for a specific Client nutritional needs
     * @param id    represents the type of client
     * @return  the clients nutritional needs
     */
    public static int[] getClientNeed(int id) {
        return client_daily_needs[id-1].getNutrition();
    }

    /**
     * removeFromDatabase removes the item that is used by a hamper from the database that contains all the foods in the inventory 
     * @param itemID    represents which food is used
     */
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

    /**
     * downloadDatabase gets all the food from the database and sets it to the inventory 
     */
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
                FoodItem item = new FoodItem(itemID, Name, new int[] {GrainContent*Calories/100, FVContent*Calories/100, ProContent*Calories/100, Other*Calories/100, Calories});
                inventory.add(item);
            }

            // Get Client Needs
            results = myStmt.executeQuery("SELECT * FROM daily_client_needs");
            NutritionContent[] needs = new NutritionContent[4];
            int i = 0;
            while (results.next()) {
                int WholeGrains = results.getInt("WholeGrains");
                int FruitVeggies = results.getInt("FruitVeggies");
                int Protein = results.getInt("Protein");
                int Other = results.getInt("Other");
                int Calories = results.getInt("Calories");
                needs[i] = new NutritionContent(WholeGrains*Calories/100, FruitVeggies*Calories/100, Protein*Calories/100, Other*Calories/100, Calories);
                // needs[i] = new NutritionContent(WholeGrains*Calories/100*7, FruitVeggies*Calories/100*7, Protein*Calories/100*7, Other*Calories/100*7, Calories*7);
                i++;
            }
            client_daily_needs = needs;

            myStmt.close();
        } catch (SQLException e) {  //Throws a SQL exeption is something when't wrong with the SQL
            e.printStackTrace();
        }
    }

    /**
     * addFoodItem adds a food item to the inventory
     * @param toBeAdded is the food item added
     */
    public static void addFoodItem(FoodItem toBeAdded)
    {
        inventory.add(toBeAdded);
    }

    /**
     * deleteFoodItem removes a food item from the inventory
     * @param toBeRemoved   is the food item removed
     */
    public static void deleteFoodItem(FoodItem toBeRemoved)
    {
        inventory.remove(toBeRemoved);
    }
}

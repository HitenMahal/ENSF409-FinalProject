package tests.edu.ucalgary.ensf409;

import edu.ucalgary.ensf409.*;

public class TestDatabaseConnection {
    public static void main(String[] args) {
        Inventory.getInventory().clear();
        Inventory.downloadDatabase();
        System.out.println(Inventory.getInventory().toArray().toString());
    }
}

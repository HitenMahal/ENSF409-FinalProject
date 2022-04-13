package edu.ucalgary.ensf409;

public class DatabaseConnectionError extends Exception{
    public DatabaseConnectionError() {
        super("Error connecting to database");
    }
}

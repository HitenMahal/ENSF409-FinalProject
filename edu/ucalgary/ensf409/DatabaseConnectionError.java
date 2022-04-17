package edu.ucalgary.ensf409;

/**
 * DatabaseConnectionError throws a database connection error when an error occurs when connecting to the database
 */
public class DatabaseConnectionError extends Exception{
    public DatabaseConnectionError() {
        super("Error connecting to database");
    }
}

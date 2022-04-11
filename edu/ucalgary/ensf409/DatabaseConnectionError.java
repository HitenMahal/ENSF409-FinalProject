package edu.ucalgary.ensf409;

public class DatabaseConnectionError extends Exception{
    DatabaseConnectionError() {
        super("Error connecting to database");
    }
}

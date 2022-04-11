package edu.ucalgary.ensf409;

public class InsufficientFoodException extends Exception{
    InsufficientFoodException() {
        super("Inventory does not contain sufficient food to fulfill order");
    }
}

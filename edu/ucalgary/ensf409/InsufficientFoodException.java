package edu.ucalgary.ensf409;
/**
 * InsufficientFoodException throws a exception when there is not enough food to fulfill the order
 */
public class InsufficientFoodException extends Exception{
    InsufficientFoodException() {
        super("Inventory does not contain sufficient food to fulfill order");
    }
}

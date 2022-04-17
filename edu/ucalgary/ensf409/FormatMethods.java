package edu.ucalgary.ensf409;
/**
 * FormatMethods is used to provide the details of the food for the clients when needed
 */
interface FormatMethods {
    public String getFormattedDetailsForUser(); //this retrieves the details of the food for the client
    public String toStringRepresentation();     //this then converts it to a string and is then printed
}

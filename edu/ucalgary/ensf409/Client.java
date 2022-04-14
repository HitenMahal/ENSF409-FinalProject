package edu.ucalgary.ensf409;

/**
 * Client object represents one person that is relying on a hamper. A hamper may have multiple clients in a household that it serves
 * Client NutritionNeeds are set using the values in the database at runtime
 * Client ID (1, 2, 3, 4) = Type ("Adult Male", "Adult Female", "Child under 8", "Child over")
 */
public class Client implements FormatMethods{
    private final int CLIENT_ID;
    private final String TYPE;
    private final NutritionContent NUTRITION_CONTENT;
    /**
     * Constructor for a Client Object. Uses client ID to set appropriate client type and appropriate client needs
     * @param client_ID
     */
    public Client(int client_ID){
        this.CLIENT_ID = client_ID;
        switch(client_ID) {
            case 1: 
                this.TYPE = "Adult Male";
                break;
            case 2: 
                this.TYPE = "Adult Female";
                break;
            case 3:
                this.TYPE = "Child Over 8";
                break;
            case 4:
                this.TYPE = "Child Under 8";
                break;
            default:
                throw new IllegalArgumentException();
        }
        this.NUTRITION_CONTENT = new NutritionContent( Inventory.getClientNeed(client_ID) );
    }

    /**
     * Getter for Client ID
     * @return Client ID (either 1,2,3 or 4)
     */
    public int getClientID(){
        return this.CLIENT_ID;
    }

    /**
     * Getter for Client Type
     * @return Client Type (either "Adult Male", "Adult Female", "Child under 8" or "Child over")
     */
    public String getType(){
        return this.TYPE;
    }

    /**
     * Getter for Nutritional Needs of the client
     * @return NutritionContent object representing the clients needs
     */
    public NutritionContent getNutritionNeeds(){
        return this.NUTRITION_CONTENT;
    }

    /**
     * Creates a string representation of the Client to use in a reciept or display to user
     * @return A string representing the Client for the user (no newline character at ending)
     */
    @Override
    public String getFormattedDetailsForUser() {
        return TYPE;
    }

    /**
     * Creates a string representation of the Clients for non-user use, contains details such as values of all internal variables
     */
    @Override
    public String toStringRepresentation() {
        return TYPE;
    }
    
}

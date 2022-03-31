package edu.ucalgary.ensf409;

public class Client {
    private final int CLIENT_ID;
    private final String TYPE;
    private final NutritionalContent NUTRITIONAL_NEEDS;
    public Client(int client_ID){
        this.CLIENT_ID = client_ID;
        this.TYPE ="";
        this.NUTRITIONAL_NEEDS = new NutritionalContent(0, 0, 0, 0, 0);
    }
    public int getClientID(){
        return this.CLIENT_ID;
    }
    public String getType(){
        return this.TYPE;
    }
    public NutritionalContent getNutritionalNeeds(){
        return this.NUTRITIONAL_NEEDS;
    }
    
}

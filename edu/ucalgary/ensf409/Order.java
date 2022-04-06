package edu.ucalgary.ensf409;

/**
 * Order object represents a user's order through the GUI. The order may be for multiple hampers that need to feed multiple people
 */
public class Order {
    private final String[][] REQUEST;
    private Hamper[] hampers;
    
    /**
     * Order constructor, uses request to create hampers which can be retrieved using getHampers()
     * Calls createHampers()
     * @param request
     */
    Order(String[][] request) {
        this.REQUEST = request;
        createHampers();
    }

    /**
     * Uses the order requests to calculate all requested hampers and populate a Hamper[]
     * The calculated hampers can be retrieved using getHampers()
     */
    public void createHampers() {
        Hamper[] calculatedHampers = new Hamper[REQUEST.length];

        int i = 0;
        for (String[] hamperRequest : REQUEST) {
            calculatedHampers[i] = CalculateHamper.calculateHamper(hamperRequest);
            i++;
        }
        this.hampers = calculatedHampers;
    }

    /**
     * Retrieves the calculated Hamper[] to fulfill the order
     * @return Hamper[] of calculated hampers
     */
    public Hamper[] getHampers() {
        return hampers;
    }
}

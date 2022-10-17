package Result;

/**
 * Result for if filling a user's tree was successful or not
 * If successful, returns how many people and events were added
 */
public class FillResult extends Result {
    /**
     * Number of Persons that were added
     */
    private int numPersons;

    /**
     * Number of Events that were added
     */
    private int numEvents;


    /**
     * Constructor that takes in number of Persons and Events that were added
     * @param numPersons - Number of Persons that were added
     * @param numEvents - Number of Events that were added
     * @param message - Error or Success message
     * @param success - Whether the request was successful or not
     */
    public FillResult(int numPersons, int numEvents, String message, boolean success) {
        super(message, success);
        this.numPersons = numPersons;
        this.numEvents = numEvents;
    }

    /**
     * Default Constructor
     */
    public FillResult() {}

    public int getNumPersons() {
        return numPersons;
    }

    public void setNumPersons(int numPersons) {
        this.numPersons = numPersons;
    }

    public int getNumEvents() {
        return numEvents;
    }

    public void setNumEvents(int numEvents) {
        this.numEvents = numEvents;
    }

}

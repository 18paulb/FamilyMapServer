package Result;

/**
 * Result for if clearing database and loading new info was successful
 * If successful, return how number of Users, Persons, and Events that were added
 */
public class LoadResult extends Result {

    /**
     * Number of Users that were added
     */
    private int numUsers;

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
     * @param numUsers - Number of Users that were added
     * @param numPersons - Number of Persons that were added
     * @param numEvents - Number of Events that were added
     * @param message - Error or Success message
     * @param success - Whether the request was successful or not
     */
    public LoadResult(int numUsers, int numPersons, int numEvents, String message, boolean success) {
        super(message, success);
        this.numUsers = numUsers;
        this.numPersons = numPersons;
        this.numEvents = numEvents;
    }

    public LoadResult(String message, boolean success) {
        super(message, success);
    }

    /**
     * Default Constructor
     */
    public LoadResult() {}


    public int getNumUsers() {
        return numUsers;
    }

    public void setNumUsers(int numUsers) {
        this.numUsers = numUsers;
    }

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

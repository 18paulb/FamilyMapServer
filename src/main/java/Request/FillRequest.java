package Request;

/**
 * Request to fill a User's tree with data
 */
public class FillRequest {
    /**
     * Username of the user to add Persons too
     */
    private String username;

    /**
     * The number of generations to add
     */
    private int numGenerations;

    /**
     * Constructor that takes in number of Persons and Events to create
     * @param username - Username of User to fill
     * @param numGenerations - Number of generations to create
     */
    public FillRequest(String username, int numGenerations) {
        this.username = username;
        this.numGenerations = numGenerations;
    }

    /**
     * Default Constructor
     */
    public FillRequest() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getNumGenerations() {
        return numGenerations;
    }

    public void setNumGenerations(int numGenerations) {
        this.numGenerations = numGenerations;
    }
}

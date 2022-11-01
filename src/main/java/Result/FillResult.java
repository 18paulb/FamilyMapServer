package Result;

/**
 * Result for if filling a user's tree was successful or not
 * If successful, returns how many people and events were added
 */
public class FillResult extends Result {

    /**
     * Constructor that takes in number of Persons and Events that were added
     * @param message - Error or Success message
     * @param success - Whether the request was successful or not
     */
    public FillResult(String message, boolean success) {
        super(message, success);
    }

    /**
     * Default Constructor
     */
    public FillResult() {}

}

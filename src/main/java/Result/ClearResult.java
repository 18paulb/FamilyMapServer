package Result;

/**
 * Result if clearing the database was successful or not
 */
public class ClearResult extends Result {
    /**
     * Constructor for ClearResult
     * @param message - Message to be displayed
     * @param success - Boolean value for whether successful or not
     */
    public ClearResult(String message, boolean success) {
        super(message, success);
    }

    /**
     * Default Constructor
     */
    public ClearResult() {}


}

package DataAccess;

/**
 * Exception thrown if there are any data access problems
 */
public class DataAccessException extends Exception {

    /**
     * Constructor
     * @param message - Exception Message
     */
    DataAccessException(String message) {
        super(message);
    }
}

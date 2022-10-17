package Result;

/**
 * All result classes have message and success, will inherit from this to avoid code duplication
 */
public class Result {
    /**
     * Error or Success Message
     */
    private String message;

    /**
     * Whether the Request was successful or not
     */
    private boolean success;

    /**
     * Constructor
     * @param message - Error or Success Message
     * @param success - Whether the Request was successful or not
     */
    Result(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    /**
     * Default Constructor
     */
    Result() {}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

package Request;

/**
 * Request to find all Events connected to current User
 */
public class GetAllEventRequest {

    /**
     * Authentication Token for the user
     */
    private String token;

    /**
     * Constructor
     * @param token - Authentication Token for the user
     */
    public GetAllEventRequest(String token) {
        this.token = token;
    }

    /**
     * Default Constructor
     */
    public GetAllEventRequest() {}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

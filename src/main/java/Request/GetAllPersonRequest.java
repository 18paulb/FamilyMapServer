package Request;

/**
 * Request to find all Persons connected to current User
 */
public class GetAllPersonRequest {

    /**
     * Authentication Token for the user
     */
    private String token;

    /**
     * Constructor
     * @param token - Authentication Token for the user
     */
    public GetAllPersonRequest(String token) {
        this.token = token;
    }

    /**
     * Default Constructor
     */
    public GetAllPersonRequest() {}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

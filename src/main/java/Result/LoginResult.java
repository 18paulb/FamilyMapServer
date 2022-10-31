package Result;

/**
 * Result for if logging in was successful or not
 * If successful, return the username and personID of User
 */
public class LoginResult extends Result {
    /**
     * The generated authToken from a successful result
     */
    private String authToken;

    /**
     * The logging in User's username
     */
    private String username;

    /**
     * The logging in User's personID
     */
    private String personID;

    public LoginResult(String authToken, String username, String personID, String message, boolean success) {
        super(message, success);
        this.authToken = authToken;
        this.username = username;
        this.personID = personID;
    }

    public LoginResult(String message, boolean success) {
        super(message, success);
    }
    public LoginResult() {}

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }
}

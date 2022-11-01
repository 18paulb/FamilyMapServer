package Result;

/**
 * Result for if logging in was successful or not
 * If successful, return the username and personID of User
 */
public class LoginResult extends Result {
    /**
     * The generated authToken from a successful result
     */
    private String authtoken;

    /**
     * The logging in User's username
     */
    private String username;

    /**
     * The logging in User's personID
     */
    private String personID;

    public LoginResult(String authToken, String username, String personID, boolean success) {
        super(success);
        this.authtoken = authToken;
        this.username = username;
        this.personID = personID;
    }

    public LoginResult(String message, boolean success) {
        super(message, success);
    }
    public LoginResult() {}

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
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

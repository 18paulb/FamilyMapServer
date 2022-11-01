package Result;

/**
 * Result for if registering new User successful or not
 * If successful, return the authToken, username. and personID of the User
 */
public class RegisterResult extends Result{
    /**
     * Generated AuthToken
     */
    private String authtoken;

    /**
     * Registered Username
     */
    private String username;

    /**
     * Generated unique personID for registered User
     */
    private String personID;

    /**
     * Constructor for the Register Result
     * @param authToken - Generated AuthToken
     * @param username - Username for the registered User
     * @param personID - PersonID for the registered User
     * @param success - Whether register was successful or not
     */
    public RegisterResult(String authToken, String username, String personID, boolean success) {
        super(success);
        this.authtoken = authToken;
        this.username = username;
        this.personID = personID;
    }

    public RegisterResult(String message, boolean success) {
        super(message, success);
    }
    /**
     * Default Constructor
     */
    public RegisterResult() {}

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

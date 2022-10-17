package Result;

import Request.RegisterRequest;

/**
 * Result for if registering new User successful or not
 * If successful, return the authToken, username. and personID of the User
 */
public class RegisterResult extends Result{
    /**
     * Generated AuthToken
     */
    private String authToken;

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
     * @param message - Error or Success message
     * @param success - Whether register was successful or not
     */
    public RegisterResult(String authToken, String username, String personID, String message, boolean success) {
        super(message, success);
        this.authToken = authToken;
        this.username = username;
        this.personID = personID;
    }

    /**
     * Default Constructor
     */
    public RegisterResult() {}

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

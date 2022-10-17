package Request;

/**
 * Request to find specific Person using eventID
 */
public class FindPersonRequest {
    /**
     * ID of the person to be loaded
     */
    private String personID;

    /**
     * AuthToken required to do search
     */
    private String authToken;

    /**
     * Constructor
     * @param ID - PersonID to be found
     * @param token - Authtoken required to do search
     */
    public FindPersonRequest(String ID, String token) {
        this.personID = ID;
        this.authToken = token;
    }

    /**
     * Default Constructor
     */
    public FindPersonRequest() {}


    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}

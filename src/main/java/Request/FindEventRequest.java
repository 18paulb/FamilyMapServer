package Request;

/**
 * Request to find specific Event using eventID
 */
public class FindEventRequest {

    /**
     * ID of Event to be found
     */
    private String eventID;

    /**
     * Authtoken required to do search
     */
    private String authToken;

    /**
     * Constructor
     * @param eventID - ID of Event to be found
     * @param authToken - Authtoken required to do search
     */
    public FindEventRequest(String eventID, String authToken) {
        this.eventID = eventID;
        this.authToken = authToken;
    }

    /**
     * Default Constructor
     */
    public FindEventRequest() {}

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}

package Result;

import Model.Event;
import Model.Person;

/**
 * Result for if finding Event was successful or not
 * If successful, returns the found Event
 */
public class FindEventResult extends Result {

    /**
     * The found event
     */
    private Event event;

    private String associatedUsername;
    private String eventID;
    private String personID;
    private float latitude;
    private float longitude;
    private String country;
    private String city;
    private String eventType;
    private int year;

    /**
     * Constructor
     * @param event - Event that was found
     * @param message - Error or Success message
     * @param success - Boolean value whether search was successful
     */
    public FindEventResult(Event event, boolean success) {
        super(success);
        this.associatedUsername = event.getAssociatedUsername();
        this.eventID = event.getEventID();
        this.personID = event.getPersonID();
        this.latitude = event.getLatitude();
        this.longitude = event.getLongitude();
        this.country = event.getCountry();
        this.city = event.getCity();
        this.eventType = event.getEventType();
        this.year = event.getYear();
    }

    public FindEventResult(String message, boolean success) {
        super(message, success);
    }


    /**
     * Default Constructor
     */
    public FindEventResult() {}

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}

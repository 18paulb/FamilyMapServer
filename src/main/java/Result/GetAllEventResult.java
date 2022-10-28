package Result;

import Model.Event;
import Model.Person;

import java.util.List;

/**
 * Result for if finding Events was successful or not
 * If successful, returns all found Events
 */
public class GetAllEventResult extends Result {

    /**
     * All found events connected to User
     */
    private List<Event> events;

    /**
     * Constructor
     * @param events - All found Events connected to User
     * @param message - Error or Success Message
     * @param success - Boolean value whether successfully or not
     */
    public GetAllEventResult(List<Event> events, String message, boolean success) {
        super(message, success);
        this.events = events;
    }

    public GetAllEventResult(String message, boolean success) {
        super(message, success);
    }

    public GetAllEventResult(List<Event> events, boolean success) {
        super(success);
        this.events = events;
    }

    /**
     * Default Constructor
     */
    public GetAllEventResult() {}

    /**
     * Adds a Event to events List
     * @param event - Person to be added
     */
    public void addEvent(Event event) {
        events.add(event);
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}

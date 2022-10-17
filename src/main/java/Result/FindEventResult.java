package Result;

import Model.Event;

/**
 * Result for if finding Event was successful or not
 * If successful, returns the found Event
 */
public class FindEventResult extends Result {

    /**
     * The found event
     */
    private Event event;

    /**
     * Constructor
     * @param event - Event that was found
     * @param message - Error or Success message
     * @param success - Boolean value whether search was successful
     */
    public FindEventResult(Event event, String message, boolean success) {
        super(message, success);
        this.event = event;
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

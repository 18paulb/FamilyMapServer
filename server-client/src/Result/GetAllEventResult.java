package Result;

import Model.Event;

import java.util.ArrayList;

/**
 * Result for if finding Events was successful or not
 * If successful, returns all found Events
 */
public class GetAllEventResult extends Result {

    /**
     * All found events connected to User
     */
    //private Event[] events;
    private ArrayList<Event> data;

    /**
     * Constructor
     * @param events - All found Events connected to User
     * @param message - Error or Success Message
     * @param success - Boolean value whether successfully or not
     */
    public GetAllEventResult(ArrayList<Event> events, String message, boolean success) {
        super(message, success);
        this.data = events;
    }

    public GetAllEventResult(String message, boolean success) {
        super(message, success);
    }

    public GetAllEventResult(ArrayList<Event> events, boolean success) {
        super(success);
        this.data = events;
    }

    /**
     * Default Constructor
     */
    public GetAllEventResult() {}

    public ArrayList<Event> getData() {
        return data;
    }

    public void setData(ArrayList<Event> data) {
        this.data = data;
    }
}

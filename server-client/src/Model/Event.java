package Model;

import Data.Location;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * Model for an Event
 */
public class Event {
    /**
     * Unique ID for the event
     */
    private String eventID;
    /**
     * Username of the user this event is connected to
     */
    private String associatedUsername;
    /**
     * ID of the person this event is connected to
     */
    private String personID;
    /**
     * Latitude of event location
     */
    private float latitude;
    /**
     * Longitude of event location
     */
    private float longitude;
    /**
     * Country of event location
     */
    private String country;
    /**
     * City of event location
     */
    private String city;
    /**
     * Type of the event
     */
    private String eventType;
    /**
     * Year event takes place
     */
    private int year;

    /**
     * Creates new Event
     * @param eventID - Unique ID of the event
     * @param associatedUsername - Username of the user this event is connected to
     * @param personID - ID of the person this event is connected to
     * @param latitude - Latitude of event location
     * @param longitude - Longitude of event location
     * @param country - Country of event location
     * @param city - City of event location
     * @param eventType - Type of the event
     * @param year - Year event takes place
     */
    public Event(String eventID, String associatedUsername, String personID, float latitude, float longitude,
        String country, String city, String eventType, int year) {
        this.eventID = eventID;
        this.associatedUsername = associatedUsername;
        this.personID =  personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
    }
    public Event() {}

    public void generateRandomEventID() {
        this.eventID = UUID.randomUUID().toString().substring(0,10);
    }

    public static Event createEvent(String eventType, String associatedUsername, Person person, Location loc, int year) throws IOException {
        Event event = new Event();
        event.generateRandomEventID();
        event.setAssociatedUsername(associatedUsername);
        event.setPersonID(person.getPersonID());
        event.setLatitude(loc.getLatitude());
        event.setLongitude(loc.getLongitude());
        event.setCountry(loc.getCountry());
        event.setCity(loc.getCity());
        event.setEventType(eventType);
        //FIXME: Change
        event.setYear(year);

        return event;
    }

    public String getEventID() {return eventID;}

    public void setEventID(String eventID) {this.eventID = eventID;}

    public String getAssociatedUsername() {return associatedUsername;}

    public void setAssociatedUsername(String associatedUsername) {this.associatedUsername = associatedUsername;}

    public String getPersonID() {return personID;}

    public void setPersonID(String personID) {this.personID = personID;}

    public float getLatitude() {return latitude;}

    public void setLatitude(float latitude) {this.latitude = latitude;}

    public float getLongitude() {return longitude;}

    public void setLongitude(float longitude) {this.longitude = longitude;}

    public String getCountry() {return country;}

    public void setCountry(String country) {this.country = country;}

    public String getCity() {return city;}

    public void setCity(String city) {this.city = city;}

    public String getEventType() {return eventType;}

    public void setEventType(String eventType) {this.eventType = eventType;}

    public int getYear() {return year;}

    public void setYear(int year) {this.year = year;}

    /**
     * Overwritten equals function
     * @param o - Object to compare to
     * @return - Boolean value if two objects are equal or not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(eventID, event.eventID) && Objects.equals(associatedUsername, event.associatedUsername) && Objects.equals(personID, event.personID) && Objects.equals(latitude, event.latitude) && Objects.equals(longitude, event.longitude) && Objects.equals(country, event.country) && Objects.equals(city, event.city) && Objects.equals(eventType, event.eventType) && Objects.equals(year, event.year);
    }
}

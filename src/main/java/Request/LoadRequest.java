package Request;

import Model.*;

import java.util.List;

/**
 * Clears all data from database then loads user, person, and events to the database
 */
public class LoadRequest {
    /**
     * Users to be added to the database
     */
    private List<User> users;

    /**
     * Persons to be added to the database
     */
    private List<Person> persons;

    /**
     * Events to be added to the database
     */
    private List<Event> events;

    /**
     * Constructor
     * @param users - Users to be added
     * @param persons - Persons to be added
     * @param events - Events to be added
     */
    public LoadRequest(List<User> users, List<Person> persons, List<Event> events) {
        this.users = users;
        this.persons = persons;
        this.events = events;
    }

    /**
     * Default Constructor
     */
    public LoadRequest() {}


    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}

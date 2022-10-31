package Result;

import Model.Person;

/**
 * Result for if finding Person was successful or not
 * If successful, returns the found Person
 */
public class FindPersonResult extends Result {

    private Person person;

    /**
     * Constructor
     * @param person - The found person
     */
    public FindPersonResult(Person person, String message, boolean success) {
        super(message, success);
        this.person = person;
    }

    public FindPersonResult(String message, boolean success) {
        super(message, success);
    }

    public FindPersonResult(Person person, boolean success) {
        super(success);
        this.person = person;
    }

    /**
     * Default Constructor
     */
    public FindPersonResult() {}

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}

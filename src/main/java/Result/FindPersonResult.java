package Result;

import Model.Person;

/**
 * Result for if finding Person was successful or not
 * If successful, returns the found Person
 */
public class FindPersonResult extends Result {

    Person person;

    /**
     * Constructor
     * @param person - The found person
     */
    public FindPersonResult(Person person) {
        this.person = person;
    }

    /**
     * Default Constructor
     */
    public FindPersonResult() {}

}

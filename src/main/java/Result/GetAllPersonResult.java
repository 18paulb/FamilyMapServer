package Result;

import Model.Person;

import java.util.List;

/**
 * Result for if finding Persons was successful or not
 * If successful, returns all found Persons
 */
public class GetAllPersonResult extends Result {

    /**
     * All found persons connected to User
     */
    private List<Person> persons;

    /**
     * Constructor
     * @param persons - All found Persons connected to User
     * @param message - Error or Success Message
     * @param success - Boolean value whether successfully or not
     */
    public GetAllPersonResult(List<Person> persons, String message, boolean success) {
        super(message, success);
        this.persons = persons;
    }

    /**
     * Default Constructor
     */
    public GetAllPersonResult() {}

    /**
     * Adds a Person to persons List
     * @param person - Person to be added
     */
    public void addPerson(Person person) {
        persons.add(person);
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}

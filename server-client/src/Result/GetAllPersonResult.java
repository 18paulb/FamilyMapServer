package Result;

import Model.Person;

import java.util.ArrayList;

/**
 * Result for if finding Persons was successful or not
 * If successful, returns all found Persons
 */
public class GetAllPersonResult extends Result {

    /**
     * All found persons connected to User
     */
    //private List<Person> persons;
    //private Person[] persons;
    private ArrayList<Person> data;

    /**
     * Constructor
     * @param persons - All found Persons connected to User
     * @param message - Error or Success Message
     * @param success - Boolean value whether successfully or not
     */
    public GetAllPersonResult(ArrayList<Person> persons, String message, boolean success) {
        super(message, success);
        this.data = persons;
    }

    public GetAllPersonResult(String message, boolean success) {
        super(message, success);
    }

    public GetAllPersonResult(ArrayList<Person> persons, boolean success) {
        super(success);
        this.data = persons;
    }

    /**
     * Default Constructor
     */
    public GetAllPersonResult() {}

    public ArrayList<Person> getData() {
        return data;
    }

    public void setData(ArrayList<Person> data) {
        this.data = data;
    }
}

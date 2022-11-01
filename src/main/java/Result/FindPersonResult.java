package Result;

import Model.Person;

/**
 * Result for if finding Person was successful or not
 * If successful, returns the found Person
 */
public class FindPersonResult extends Result {

    //private Person person;

    private String personID;
    /**
     * The associatedUsername for which User this person is connected to
     */
    private String associatedUsername;
    /**
     * Person's first name
     */
    private String firstName;
    /**
     * Person's last name
     */
    private String lastName;
    /**
     * Persons' gender
     */
    private String gender;
    /**
     * Unique ID of the father IF APPLICABLE
     */
    private String fatherID;
    /**
     * Unique ID of the mother IF APPLICABLE
     */
    private String motherID;
    /**
     * Unique ID of the spouse IF APPLICABLE
     */
    private String spouseID;



    /**
     * Constructor
     * @param person - The found person
     */

    public FindPersonResult(Person person, String message, boolean success) {
        super(message, success);
        //this.person = person;
    }


    public FindPersonResult(String message, boolean success) {
        super(message, success);
    }

    public FindPersonResult(Person person, boolean success) {
        super(success);
        this.personID = person.getPersonID();
        this.associatedUsername = person.getAssociatedUsername();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.gender = person.getGender();
        this.fatherID = person.getFatherID();
        this.motherID = person.getMotherID();
        this.spouseID = person.getSpouseID();
    }

    /**
     * Default Constructor
     */
    public FindPersonResult() {}

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFatherID() {
        return fatherID;
    }

    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    public String getMotherID() {
        return motherID;
    }

    public void setMotherID(String motherID) {
        this.motherID = motherID;
    }

    public String getSpouseID() {
        return spouseID;
    }

    public void setSpouseID(String spouseID) {
        this.spouseID = spouseID;
    }
}

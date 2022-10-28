package Model;

import java.util.Objects;
import java.util.UUID;

/**
 * Model for a Person
 */
public class Person {
    /**
     * Unique ID for the person
     */
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
     * Creates new Person
     * @param personID - Unique ID for the person
     * @param associatedUsername - Username of the user this person is connected to
     * @param firstName - Person's first name
     * @param lastName - Person's last name
     * @param gender - Person's gender
     */
    public Person(String personID, String associatedUsername, String firstName, String lastName, String gender) {
        this.personID = personID;
        this.associatedUsername = associatedUsername;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    /**
     * Constructor that includes father, mother, and spouse ID's
     * @param personID - Unique ID for the person
     * @param associatedUsername - Username of the user this person is connected to
     * @param firstName - Person's first name
     * @param lastName - Person's last name
     * @param gender - Person's gender
     * @param fatherID - ID of Person's father
     * @param motherID - ID of Person's mother
     * @param spouseID - ID of Person's spouse
     */
    public Person(String personID, String associatedUsername, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID) {
        this.personID = personID;
        this.associatedUsername = associatedUsername;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
    }

    public Person() {}

    public void generateRandomPersonID() {
        this.personID = UUID.randomUUID().toString().substring(0,10);
    }

    public String getPersonID() { return personID; }

    public void setPersonID(String personID) {this.personID = personID;}

    public String getAssociatedUsername() {return associatedUsername;}

    public void setAssociatedUsername(String associatedUsername) {this.associatedUsername = associatedUsername;}

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getGender() {return gender;}

    public void setGender(String gender) {this.gender = gender;}

    /**
     * Overwritten equals function
     * @param o - Object to compare too
     * @return - Boolean value whether the two Persons are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(personID, person.personID) && Objects.equals(associatedUsername, person.associatedUsername) &&
                Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(gender, person.gender) &&
                Objects.equals(fatherID, person.fatherID) && Objects.equals(motherID, person.motherID) && Objects.equals(spouseID, person.spouseID);
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

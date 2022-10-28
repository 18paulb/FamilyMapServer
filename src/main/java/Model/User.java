package Model;

import java.util.Objects;
import java.util.UUID;

/**
 * Model For User
 */
public class User {

    /**
     * User's username
     */
    private String username;
    /**
     * User's password
     */
    private String password;
    /**
     * User's email
     */
    private String email;
    /**
     * User's first name
     */
    private String firstName;
    /**
     * User's last name
     */
    private String lastName;
    /**
     * User's gender
     */
    private String gender;
    /**
     * Unique Person ID assigned to this user's generated Person
     */
    private String personID;

    /**
     * Creates new user
     * @param username - User's username
     * @param password - User's password
     * @param email - User's email
     * @param firstName - User's first name
     * @param lastName - User's last name
     * @param gender - User's gender
     */

    public User(String username, String password, String email, String firstName, String lastName, String gender, String personID) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.personID = personID;
    }

    public User(String username, String password, String email, String firstName, String lastName, String gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;

        generateRandomPersonID();
    }

    public User() {}

    public void generateRandomPersonID() {
        this.personID = UUID.randomUUID().toString().substring(0,10);
    }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getPersonID() { return personID; }
    public void setPersonID(String personID) { this.personID = personID; }

    /**
     * Overwritten equals function
     * @param o - Object to compare too
     * @return - Boolean value whether the two Users are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(email, user.email) &&
                Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(gender, user.gender) && Objects.equals(personID, user.personID);
    }
}

package Request;

/**
 * Request to register new User into the database
 */
public class RegisterRequest {
    /**
     * Username to be registered
     */
    private String username;

    /**
     * Password to be registered
     */
    private String password;

    /**
     * Email to be registered
     */
    private String email;

    /**
     * First name to be registered
     */
    private String firstName;

    /**
     * Last name to be registered
     */
    private String lastName;

    /**
     * Gender to be registered
     */
    private String gender;

    /**
     * PersonID of the Registered Person
     */
    private String personID;

    /**
     * Constructor
     * @param username - Username to be registered
     * @param password - Password to be registered
     * @param email - Email to be registered
     * @param first - First name to be registered
     * @param last - Last name to be registered
     * @param gender - Gender to be registered
     */
    public RegisterRequest(String username, String password, String email, String first, String last, String gender, String personID) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = first;
        this.lastName = last;
        this.gender = gender;
        this.personID = personID;
    }

    public RegisterRequest(String username, String password, String email, String first, String last, String gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = first;
        this.lastName = last;
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }
}

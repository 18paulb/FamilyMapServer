package Request;

/**
 * Request to log in user into the database
 */
public class LoginRequest {
    /**
     * Username of the login request
     */
    private String username;
    /**
     * Password of the login request
     */
    private String password;

    /**
     * Constructor that takes in User info
     * @param username - User's username
     * @param password - User's password
     */
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginRequest() {}

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
}

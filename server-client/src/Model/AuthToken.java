package Model;

import java.util.Objects;
import java.util.UUID;

/**
 * Model for an AuthToken
 */
public class AuthToken {

    /**
     * Unique Authentication Token used API requests
     */
    private String authtoken;
    /**
     * Username that is associated with the authToken
     */
    private String username;

    /**
     * Creates new AuthToken
     * @param authToken - Unique AuthToken
     * @param username  - Username that is associated with the authToken
     */
    public AuthToken(String authToken, String username) {
        this.authtoken = authToken;
        this.username = username;
    }

    public AuthToken(String username) {
        this.username = username;

        generateRandomAuthToken();
    }

    public AuthToken() {
    }

    public void generateRandomAuthToken() {
        this.authtoken = UUID.randomUUID().toString();
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Overwritten equals function
     *
     * @param o - Object to compare too
     * @return - Boolean value whether the two AuthTokens are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthToken token = (AuthToken) o;
        return Objects.equals(authtoken, token.authtoken) && Objects.equals(username, token.username);
    }
}
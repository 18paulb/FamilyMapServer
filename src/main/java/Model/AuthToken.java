package Model;

import java.util.Objects;

/**
 * Model for an AuthToken
 */
public class AuthToken {

    /**
     * Unique Authentication Token used API requests
     */
    private String authToken;
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
        this.authToken = authToken;
        this.username = username;
    }

    public AuthToken() {
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
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
        return Objects.equals(authToken, token.authToken) && Objects.equals(username, token.username);
    }
}
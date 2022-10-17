package DataAccess;

import Model.AuthToken;
import Model.User;

import java.sql.*;

/**
 * Data Access Class to control database connection
 */
public class AuthTokenDao {

    /**
     * SQL Database connection
     */
    private final Connection conn;

    /**
     * Constructor for AuthTokenDao
     * @param conn - Connection to SQL Database
     */
    public AuthTokenDao(Connection conn) { this.conn = conn; }

    /**
     * Creates AuthToken in Database
     * @param token - Token to be added
     */
    void createAuthToken(AuthToken token) {}

}

package DataAccess;

import Model.AuthToken;

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
    public void createAuthToken(AuthToken token) throws DataAccessException {
        String sql = "INSERT INTO AuthToken (authtoken, username) " +
                "VALUES (?,?)";

        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, token.getAuthtoken());
            stmt.setString(2, token.getUsername());

            if (stmt.executeUpdate() == 1) {
                System.out.println("Added AuthToken into Table: " + token.getAuthtoken());
            } else {
                System.out.println("Did not add Authtoken");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
            throw new DataAccessException("Error occurred while creating Authtoken");
        }
    }

    public AuthToken find(String username) throws DataAccessException {
        String sql = "SELECT * FROM AuthToken WHERE username = ?;";

        AuthToken token = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String authToken = rs.getString(1);
                String associatedUsername = rs.getString(2);

                token = new AuthToken(authToken, associatedUsername);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
            throw new DataAccessException("Error occurred while finding Event");
        }

        return token;
    }

    public AuthToken findByToken(String authtoken) throws DataAccessException {
        String sql = "SELECT * FROM AuthToken WHERE authtoken = ?;";

        AuthToken token = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, authtoken);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String authToken = rs.getString(1);
                String associatedUsername = rs.getString(2);

                token = new AuthToken(authToken, associatedUsername);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
            throw new DataAccessException("Error occurred while finding Event");
        }

        return token;
    }

    /* NOT USED
    public void deleteAuthToken(String authtoken) throws DataAccessException {
        String sql = "DELETE FROM AuthToken WHERE authtoken = ?;";

        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, authtoken);

            if (stmt.executeUpdate() == 1) {
                System.out.println("Deleted AuthToken with ID: " + authtoken);
            } else {
                System.out.println("Did not delete authToken");
            }


        } catch (SQLException ex) {
            System.out.println(ex);
            throw new DataAccessException("Error occurred while deleting authToken");
        }
    }

     */

    /* NOT USED
    public void clearTable() throws SQLException, DataAccessException {
        try (Statement stmt = conn.createStatement()) {
            String sql = "DELETE FROM AuthToken";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e);
            throw new DataAccessException("Could not clear table");
        }
    }

     */


}

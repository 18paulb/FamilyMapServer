package DataAccess;

import Model.Event;
import Model.User;
import Model.Person;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Class to manipulate/retrieve any Users in database
 */
public class UserDao {

    /**
     * SQL Database connection
     */
    private final Connection conn;

    /**
     * Constructor for UserDao
     * @param conn - Connection to SQL Database
     */
    public UserDao(Connection conn) { this.conn = conn; }

    /**
     * Creates new User in database
     * @param user - User to be added
     * @throws DataAccessException
     */
    public void createUser(User user) throws DataAccessException, SQLException {

        String sql = "INSERT INTO User (username, password, email, firstName, lastName, gender, personID) " +
            "VALUES (?,?,?,?,?,?,?)";

        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getFirstName());
            stmt.setString(5, user.getLastName());
            stmt.setString(6, user.getGender());
            stmt.setString(7, user.getPersonID());

            if (stmt.executeUpdate() == 1) {
                System.out.println("Added User into Table: " + user.getUsername());
            } else {
                System.out.println("Did not add user");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
            throw new DataAccessException("Error occurred while creating User");
        }
    }

    /**
     * Validates the Login of User
     * @param username - Username of the User
     * @param password - Password of the User
     * @return - Boolean of whether there is a User in database with associated username and password
     * @throws DataAccessException
     */
    public boolean validate(String username, String password) throws DataAccessException, SQLException {
        String sql = "SELECT * FROM User WHERE username = ? AND password = ?";

        User foundUser = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String foundUsername = rs.getString(1);
                String foundPassword = rs.getString(2);
                String email = rs.getString(3);
                String firstName = rs.getString(4);
                String lastName = rs.getString(5);
                String gender = rs.getString(6);
                String personID = rs.getString(7);

                foundUser = new User(foundUsername, foundPassword, email, firstName, lastName, gender, personID);
            }

            if (foundUser != null) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Gets User from the database
     * @param userID - ID of the user
     * @return - Returns the user if found in databse
     * @throws DataAccessException
     */
    public User getUserById(String userID) throws DataAccessException {
        String sql = "SELECT * FROM User WHERE personID = ?;";

        User foundUser = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userID);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String username = rs.getString(1);
                String password = rs.getString(2);
                String email = rs.getString(3);
                String firstName = rs.getString(4);
                String lastName = rs.getString(5);
                String gender = rs.getString(6);
                String personID = rs.getString(7);

                foundUser = new User(username, password, email, firstName, lastName, gender, personID);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
            throw new DataAccessException("Error occurred while finding User");
        }

        return foundUser;
    }

    public User getUserByUsername(String username) throws DataAccessException {
        String sql = "SELECT * FROM User WHERE username = ?;";

        User foundUser = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String un = rs.getString(1);
                String password = rs.getString(2);
                String email = rs.getString(3);
                String firstName = rs.getString(4);
                String lastName = rs.getString(5);
                String gender = rs.getString(6);
                String personID = rs.getString(7);

                foundUser = new User(un, password, email, firstName, lastName, gender, personID);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
            throw new DataAccessException("Error occurred while finding User");
        }

        return foundUser;
    }

    /**
     * Deletes user from SQL Database
     * @param userID - ID of the user to be deleted
     * @throws DataAccessException
     */
    public void deleteUser(String userID) throws DataAccessException, SQLException {
        String sql = "DELETE FROM User WHERE personID = ?;";

        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userID);

            if (stmt.executeUpdate() == 1) {
                System.out.println("Deleted User with ID: " + userID);
            } else {
                System.out.println("Did not delete user");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
            throw new DataAccessException("Error occurred while deleting User");
        }
    }

    /**
     * Fills a User's Tree with new Persons
     * @param username - Username of the User
     * @param numGenerations - Number of generations to generate
     * @throws DataAccessException
     */
    public void fillUserTree(String username, int numGenerations) throws DataAccessException {}

    /**
     * Clears Table
     */
    public void clearTable() throws SQLException, DataAccessException {
        try (Statement stmt = conn.createStatement()) {
            String sql = "DELETE FROM User";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e);
            throw new DataAccessException("Error occured while clearing the table");
        }
    }
}

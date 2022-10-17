package DataAccess;

import Model.Event;
import Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Class to manipulate/retrieve any Events in database
 */
public class EventDao {
    /**
     * SQL Database connection
     */
    private final Connection conn;

    /**
     * Constructor to create EventDao with Connection
     * @param conn - Connection to SQL Database
     */
    public EventDao(Connection conn) {
        this.conn = conn;
    }

    /**
     * Inserts new event into database
     * @param event - Event to be inserted
     * @throws DataAccessException
     */
    public void insert(Event event) throws DataAccessException {
        String sql = "INSERT INTO Events (eventID, associatedUsername, personID, latitude, longitude, country, city, eventType, year) " +
                "VALUES (?,?,?,?,?,?,?,?,?)";

        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, event.getEventID());
            stmt.setString(2, event.getAssociatedUsername());
            stmt.setString(3, event.getPersonID());
            stmt.setFloat(4, event.getLatitude());
            stmt.setFloat(5, event.getLongitude());
            stmt.setString(6, event.getCountry());
            stmt.setString(7, event.getCity());
            stmt.setString(8, event.getEventType());
            stmt.setInt(9, event.getYear());

            if (stmt.executeUpdate() == 1) {
                System.out.println("Added Event into Table: " + event.getEventID());
            } else {
                System.out.println("Did not add Event");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
            throw new DataAccessException("Error occurred while creating Event");
        }
    }

    /**
     * Finds an event in the database
     * @param eventID - ID of the searched for event
     * @return - The found event
     * @throws DataAccessException
     */
    public Event find(String eventID) throws DataAccessException {
        String sql = "SELECT * FROM Events WHERE eventID = ?;";

        Event foundEvent = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, eventID);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString(1);
                String associatedUsername = rs.getString(2);
                String personID = rs.getString(3);
                float latitude = rs.getFloat(4);
                float longitude = rs.getFloat(5);
                String country = rs.getString(6);
                String city = rs.getString(7);
                String eventType = rs.getString(8);
                int year = rs.getInt(9);

                foundEvent = new Event(id, associatedUsername, personID, latitude, longitude, country, city, eventType, year);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
            throw new DataAccessException("Error occurred while finding Event");
        }

        return foundEvent;
    }

    /**
     * Finds all Events attached to the User
     * @param username - Username of the User
     * @return - List of all the events
     * @throws DataAccessException
     */
    public List<Event> findForUser(String username) throws DataAccessException, SQLException {
        String sql = "SELECT * FROM Events where associatedUsername = ?";

        List<Event> foundEvents = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString(1);
                String associatedUsername = rs.getString(2);
                String personID = rs.getString(3);
                float latitude = rs.getFloat(4);
                float longitude = rs.getFloat(5);
                String country = rs.getString(6);
                String city = rs.getString(7);
                String eventType = rs.getString(8);
                int year = rs.getInt(9);

                foundEvents.add(new Event(id, associatedUsername, personID, latitude, longitude, country, city, eventType, year));
            }
        }

        return foundEvents;
    }

    /**
     * Finds all Events attached to the User's Family Tree
     * @param username - Username of the user
     * @return - List of all the events
     * @throws DataAccessException
     */
    public List<Event> findUserTreeEvents(String username) throws DataAccessException { return new ArrayList<>(); }

    /**
     * Deletes an event from the SQL Database
     * @param eventID - ID of the event to be deleted
     * @throws DataAccessException
     */
    public void deleteEvent(String eventID) throws DataAccessException {
        String sql = "DELETE FROM Events WHERE eventID = ?;";

        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, eventID);

            if (stmt.executeUpdate() == 1) {
                System.out.println("Deleted User with ID: " + eventID);
            } else {
                System.out.println("Did not delete user");
            }


        } catch (SQLException ex) {
            System.out.println(ex);
            throw new DataAccessException("Error occurred while deleting Event");
        }
    }

    /**
     * Clears Table
     */
    public void clearTable() throws SQLException, DataAccessException {
        try (Statement stmt = conn.createStatement()) {
            String sql = "DELETE FROM Events";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e);
            throw new DataAccessException("Could not clear table");
        }

    }
}

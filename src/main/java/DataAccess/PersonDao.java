package DataAccess;

import Model.Event;
import Model.Person;
import Model.User;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Class to manipulate/retrieve any Persons in database
 */
public class PersonDao {

    /**
     * SQL Database connection
     */
    private final Connection conn;

    /**
     * Constructor for PersonDao
     * @param conn - Connection to SQL Database
     */
    public PersonDao(Connection conn) { this.conn = conn; }

    /**
     * Creates new Person in database
     * @param person - Person to be added
     * @throws DataAccessException
     */
    public void createPerson(Person person) throws DataAccessException {
        String sql = "INSERT INTO Person (personID, associatedUsername, firstName, lastName, gender, fatherID, motherID, spouseID) " +
                "VALUES (?,?,?,?,?,?,?,?)";

        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, person.getPersonID());
            stmt.setString(2, person.getAssociatedUsername());
            stmt.setString(3, person.getFirstName());
            stmt.setString(4, person.getLastName());
            stmt.setString(5, person.getGender());
            stmt.setString(6, person.getFatherID());
            stmt.setString(7, person.getMotherID());
            stmt.setString(8, person.getSpouseID());

            if (stmt.executeUpdate() == 1) {
                System.out.println("Added Person into Table: " + person.getPersonID());
            } else {
                System.out.println("Did not add Person");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
            throw new DataAccessException("Error in creating Person");
        }
    }

    /**
     * Gets Person in database that matches ID
     * @param personID - Unique ID of the person
     * @return - Person whose ID matches personID
     * @throws DataAccessException
     */
    public Person getPersonByID(String personID) throws DataAccessException {
        String sql = "SELECT * FROM Person WHERE personID = ?;";

        Person foundPerson = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, personID);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString(1);
                String associatedUsername = rs.getString(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                String gender = rs.getString(5);
                String fatherID = rs.getString(6);
                String motherID = rs.getString(7);
                String spouseID = rs.getString(8);

                foundPerson = new Person(id, associatedUsername, firstName, lastName, gender, fatherID, motherID, spouseID);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
            throw new DataAccessException("Error in Finding Person");
        }

        return foundPerson;
    }

    /**
     * Returns all the Persons connected to a User
     * @param username - Username of the user
     * @return - List of all Persons connected to a User
     * @throws DataAccessException
     */
    public ArrayList<Person> getTreeOfUser(String username) throws DataAccessException, SQLException {

        String sql = "SELECT * FROM Person where associatedUsername = ?";

        //List<Person> foundPersons = new ArrayList<>();
        ArrayList<Person> foundPersons = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString(1);
                String associatedUsername = rs.getString(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                String gender = rs.getString(5);
                String fatherID = rs.getString(6);
                String motherID = rs.getString(7);
                String spouseID = rs.getString(8);

                foundPersons.add(new Person(id, associatedUsername, firstName, lastName, gender, fatherID, motherID, spouseID));
            }
        }
        return foundPersons;
    }

    //FIXME: Change to include personID heading
    public boolean connectedToUser(String username, String personID) throws DataAccessException, SQLException {

        String sql = "SELECT * FROM Person where personID = ?";

        Person foundPerson = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, personID);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString(1);
                String associatedUsername = rs.getString(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                String gender = rs.getString(5);

                foundPerson = new Person(id, associatedUsername, firstName, lastName, gender);
            }
        }

        if (foundPerson == null) {
            return false;
        }

        boolean areConnected = false;

        if (foundPerson.getAssociatedUsername().equals(username)) {
            areConnected = true;
        }

        return areConnected;
    }

    /**
     * Deletes a Person from SQL Database
     * @param personID - ID of the Person to be deleted
     * @throws DataAccessException
     */
    public void deletePerson(String personID) throws DataAccessException {
        String sql = "DELETE FROM Person WHERE personID = ?;";

        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, personID);

            if (stmt.executeUpdate() == 1) {
                System.out.println("Deleted Person with ID: " + personID);
            } else {
                System.out.println("Did not delete Person");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
            throw new DataAccessException("Error in deleting person");
        }
    }

    /**
     * Clears Table
     */
    public void clearTable() throws SQLException, DataAccessException {
        try (Statement stmt = conn.createStatement()) {
            String sql = "DELETE FROM Person";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e);
            throw new DataAccessException("Error in clearing table");
        }

    }

}

package Tests;

import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.PersonDao;
import Model.Person;
import Model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class PersonDaoTest {
    private static Database db;
    private PersonDao personDao;

    @BeforeAll
    public static void openDatabase() {
        db = new Database();
    }


    @BeforeEach
    public void setUp() throws DataAccessException {
        Connection conn = db.openConnection();
        personDao = new PersonDao(conn);
    }

    @AfterEach
    public void closeDatabase() throws DataAccessException {
        db.closeConnection(false);
    }

    //Positive Test Cases
    @Test
    public void testAddPerson() {
        Person person = new Person("1234", "brandonpaul", "Terry", "Crews", "m",
                "fatherID", "motherID", "spouseID");

        //Adds Person to the database
        try {
            personDao.createPerson(person);
        } catch (DataAccessException ex) {
            System.out.println(ex);
        }

        //Find the added person in the database
        Person foundPerson = null;
        try {
            foundPerson = personDao.getPersonByID("1234");
        } catch (DataAccessException ex) {
            System.out.println(ex);
        }

        assertNotNull(foundPerson);
        assertEquals(person, foundPerson);
    }

    @Test
    public void testFindPerson() {
        Person person = new Person("1234", "brandonpaul", "Terry", "Crews", "m",
                "fatherID", "motherID", "spouseID");

        //Adds Person to the database
        try {
            personDao.createPerson(person);
        } catch (DataAccessException ex) {
            System.out.println(ex);
        }

        //Find the added person in the database
        Person foundPerson = null;
        try {
            foundPerson = personDao.getPersonByID("1234");
        } catch (DataAccessException ex) {
            System.out.println(ex);
        }

        assertNotNull(foundPerson);
    }

    @Test
    public void testClearTable() throws SQLException {
        Person person1 = new Person("1", "brandonpaul", "Terry", "Crews", "m");
        Person person2 = new Person("2", "brandonpaul", "Scarlet", "Johansson", "f");
        Person person3 = new Person("3", "brandonpaul", "Joe", "Mama", "m");
        Person person4 = new Person("4", "brandonpaul", "Paul", "McCartney", "m");

        try {
            personDao.createPerson(person1);
            personDao.createPerson(person2);
            personDao.createPerson(person3);
            personDao.createPerson(person4);
        } catch (DataAccessException ex) {
            System.out.println(ex);
        }

        try {
            personDao.clearTable();
        } catch (DataAccessException ex) {
            System.out.println(ex);
        }

        Person foundPerson1 = null;
        Person foundPerson2 = null;
        Person foundPerson3 = null;
        Person foundPerson4 = null;

        try {
            foundPerson1 = personDao.getPersonByID(person1.getPersonID());
            foundPerson2 = personDao.getPersonByID(person2.getPersonID());
            foundPerson3 = personDao.getPersonByID(person3.getPersonID());
            foundPerson4 = personDao.getPersonByID(person4.getPersonID());
        } catch (DataAccessException ex) {
            System.out.println(ex);
        }

        assertNull(foundPerson1);
        assertNull(foundPerson2);
        assertNull(foundPerson3);
        assertNull(foundPerson4);
    }

    //Negative Test Cases
    @Test
    public void testAddAlreadyExistingPerson() {
        Person newPerson = new Person("1234", "brandonpaul", "Tony", "Stark", "m");
        try {
            personDao.createPerson(newPerson);
        } catch (DataAccessException ex) {
            System.out.println(ex);
        }

        assertThrows(DataAccessException.class, () -> personDao.createPerson(newPerson));
    }

    @Test
    public void testFindPersonThatDoesNotExist() {
        Person foundPerson = null;
        try {
            foundPerson = personDao.getPersonByID("arbitraryID");
        } catch (DataAccessException e) {
            System.out.println(e);
        }

        assertNull(foundPerson);
    }
}

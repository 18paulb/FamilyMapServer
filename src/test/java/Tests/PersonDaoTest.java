package Tests;

import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.PersonDao;
import DataAccess.UserDao;
import Model.Person;
import Model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PersonDaoTest {
    private static Database db;
    private PersonDao personDao;
    private UserDao userDao;

    @BeforeAll
    public static void openDatabase() {
        db = new Database();
    }


    @BeforeEach
    public void setUp() throws DataAccessException {
        Connection conn = db.openConnection();
        personDao = new PersonDao(conn);
        userDao = new UserDao(conn);
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
    public void testFindPersonThatDoesNotExist() {
        Person foundPerson = null;
        try {
            foundPerson = personDao.getPersonByID("arbitraryID");
        } catch (DataAccessException e) {
            System.out.println(e);
        }

        assertNull(foundPerson);
    }

    @Test
    public void testClearTable1() throws SQLException {
        Person person1 = new Person("1", "brandonpaul", "Terry", "Crews", "m");

        try {
            personDao.createPerson(person1);
        } catch (DataAccessException ex) {
            System.out.println(ex);
        }

        try {
            personDao.clearTable();
        } catch (DataAccessException ex) {
            System.out.println(ex);
        }

        Person foundPerson1 = null;

        try {
            foundPerson1 = personDao.getPersonByID(person1.getPersonID());
        } catch (DataAccessException ex) {
            System.out.println(ex);
        }

        assertNull(foundPerson1);
    }
    @Test
    public void testClearTable4() throws SQLException {
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

   @Test
    public void getTreeOfUserTest1() {
       User user = new User("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m", "1");

       Person person1 = new Person("1", "brandonpaul", "Terry", "Crews", "m");

       ArrayList<Person> userPersons = new ArrayList<>();
       try {
           userDao.createUser(user);
           personDao.createPerson(person1);

           userPersons = personDao.getTreeOfUser(user.getUsername());
       } catch (Exception e) {
           e.printStackTrace();
       }

       int numPersons = 0;
       for (Person person : userPersons) {
           numPersons++;

           assertEquals(person.getAssociatedUsername(), user.getUsername());
       }

       assertEquals(numPersons, 1);
   }

    @Test
    public void getTreeOfUserTest4() {
        User user = new User("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m", "1");

        Person person1 = new Person("1", "brandonpaul", "Terry", "Crews", "m");
        Person person2 = new Person("2", "brandonpaul", "Scarlet", "Johansson", "f");
        Person person3 = new Person("3", "brandonpaul", "Joe", "Mama", "m");
        Person person4 = new Person("4", "brandonpaul", "Paul", "McCartney", "m");

        ArrayList<Person> userPersons = new ArrayList<>();
        try {
            userDao.createUser(user);
            personDao.createPerson(person1);
            personDao.createPerson(person2);
            personDao.createPerson(person3);
            personDao.createPerson(person4);


            userPersons = personDao.getTreeOfUser(user.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }

        int numPersons = 0;
        for (Person person : userPersons) {
            numPersons++;
            assertEquals(person.getAssociatedUsername(), user.getUsername());
        }

        assertEquals(numPersons, 4);
    }

    @Test
    public void connectedToUserTest() {
        User user = new User("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m", "1");

        Person person1 = new Person("1", "brandonpaul", "Terry", "Crews", "m");

        boolean connectedToUser = false;
        try {
            userDao.createUser(user);
            personDao.createPerson(person1);

            connectedToUser = personDao.connectedToUser(user.getUsername(), person1.getPersonID());

        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(connectedToUser);
    }

    @Test
    public void notConnectedToUserTest() {
        User user = new User("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m", "1");

        Person person1 = new Person("1", "otherperson", "Terry", "Crews", "m");

        boolean connectedToUser = true;
        try {
            userDao.createUser(user);
            personDao.createPerson(person1);

            connectedToUser = personDao.connectedToUser(user.getUsername(), person1.getPersonID());

        } catch (Exception e) {
            e.printStackTrace();
        }

        assertFalse(connectedToUser);
    }

    @Test
    public void delete1PersonTest() {
        Person person1 = new Person("1", "brandonpaul", "Terry", "Crews", "m");

        try {
            personDao.createPerson(person1);
        } catch (DataAccessException ex) {
            System.out.println(ex);
        }

        try {
            personDao.deletePerson(person1.getPersonID());
        } catch (DataAccessException ex) {
            System.out.println(ex);
        }

        Person foundPerson1 = null;

        try {
            foundPerson1 = personDao.getPersonByID(person1.getPersonID());
        } catch (DataAccessException ex) {
            System.out.println(ex);
        }

        assertNull(foundPerson1);
    }

    @Test
    public void delete4PersonTest() throws SQLException {
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
            personDao.deletePerson(person1.getPersonID());
            personDao.deletePerson(person2.getPersonID());
            personDao.deletePerson(person3.getPersonID());
            personDao.deletePerson(person4.getPersonID());
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



}

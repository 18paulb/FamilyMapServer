package Tests;

import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.UserDao;
import Model.User;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class UserDaoTest {

    private static Database db;
    private UserDao userDao;

    @BeforeAll
    public static void openDatabase() {
        db = new Database();
    }

    @BeforeEach
    public void setUp() throws DataAccessException, SQLException {

        Connection conn = db.openConnection();
        userDao = new UserDao(conn);

    }

    @AfterEach
    public void closeDatabase() throws DataAccessException {
        db.closeConnection(false);
    }

    //Positive Test Cases
    @Test
    public void testAddUser() throws SQLException, DataAccessException {
        User newUser = new User("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m", "1234");
        try {
            userDao.createUser(newUser);
        } catch (DataAccessException e) {
            System.out.println(e);
        }

        User foundUser = userDao.getUserById("1234");

        assertEquals(newUser, foundUser);

    }

    @Test
    public void testFindUser() throws SQLException, DataAccessException {
        User newUser = new User("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m", "1234");
        try {
            userDao.createUser(newUser);
        } catch (DataAccessException e) {
            System.out.println(e);
        }

        User foundUser = null;
        try {
            foundUser = userDao.getUserById("1234");
        } catch (DataAccessException e) {
            System.out.println(e);
        }

        assertNotNull(foundUser);
        assertEquals(newUser, foundUser);
    }

    @Test
    public void testClearTable() throws SQLException {
        User user1 = new User("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m", "1");
        User user2 = new User("paulmccartney", "password", "paulmccartney@gmail.com", "Paul", "McCartney", "m", "2");
        User user3 = new User("joemama", "password", "joemama@gmail.com", "Joe", "Mama", "m", "3");
        User user4 = new User("spidey", "password", "itsspidey@gmail.com", "Peter", "Parker", "m", "4");

        try {
            userDao.createUser(user1);
            userDao.createUser(user2);
            userDao.createUser(user3);
            userDao.createUser(user4);
        } catch (DataAccessException ex) {
            System.out.println(ex);
        }

        try {
            userDao.clearTable();
        } catch (DataAccessException ex) {
            System.out.println(ex);
        }
        
        User foundUser1 = null;
        User foundUser2 = null;
        User foundUser3 = null;
        User foundUser4 = null;

        try {
            foundUser1 = userDao.getUserById(user1.getPersonID());
            foundUser2 = userDao.getUserById(user2.getPersonID());
            foundUser3 = userDao.getUserById(user3.getPersonID());
            foundUser4 = userDao.getUserById(user4.getPersonID());
        } catch (DataAccessException ex) {
            System.out.println(ex);
        }

        assertNull(foundUser1);
        assertNull(foundUser2);
        assertNull(foundUser3);
        assertNull(foundUser4);
    }

    //Negative Test Cases
    @Test
    public void testAddAlreadyExistingUser() throws SQLException {
        User newUser = new User("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m", "1234");
        try {
            userDao.createUser(newUser);
        } catch (DataAccessException ex) {
            System.out.println(ex);
        }

        assertThrows(DataAccessException.class, () -> userDao.createUser(newUser));
    }

    @Test
    public void testFindUserThatDoesNotExist() {
        User foundUser = null;
        try {
            foundUser = userDao.getUserById("arbitraryID");
        } catch (DataAccessException e) {
            System.out.println(e);
        }

        assertNull(foundUser);
    }


}

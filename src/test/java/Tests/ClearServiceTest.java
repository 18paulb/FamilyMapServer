package Tests;

import DataAccess.*;
import Model.AuthToken;
import Model.Event;
import Model.Person;
import Model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNull;

public class ClearServiceTest {
    private static Database db;
    private UserDao userDao;
    private PersonDao personDao;
    private AuthTokenDao authTokenDao;
    private EventDao eventDao;

    @BeforeAll
    public static void openDatabase() {
        db = new Database();
    }

    @BeforeEach
    public void setUp() throws DataAccessException, SQLException {

        Connection conn = db.openConnection();
        userDao = new UserDao(conn);
        personDao = new PersonDao(conn);
        authTokenDao = new AuthTokenDao(conn);
        eventDao = new EventDao(conn);
    }

    @AfterEach
    public void closeDatabase() throws DataAccessException {
        db.closeConnection(false);
    }

    //Positive Test Case
    @Test
    public void testClearDatabaseOnePer() throws SQLException {
        User user = new User("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m", "1a2b");
        Person person = new Person("2", "brandonpaul", "Jerry", "Tooley", "m");
        Event event = new Event("3", "susan", "6969", (float) 235.556, (float) 124.556, "United States", "New York", "Birth", 1997);
        AuthToken token = new AuthToken("brandonpaul", "123456789");

        User foundUser = null;
        Person foundPerson = null;
        Event foundEvent = null;
        AuthToken foundToken = null;

        try {
            userDao.createUser(user);
            personDao.createPerson(person);
            eventDao.insert(event);
            authTokenDao.createAuthToken(token);

            db.clearTables();


            foundUser = userDao.getUserByUsername(user.getUsername());
            foundPerson = personDao.getPersonByID(person.getPersonID());
            foundEvent = eventDao.find(event.getEventID());
            foundToken = authTokenDao.findByToken(token.getAuthToken());

        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNull(foundUser);
        assertNull(foundPerson);
        assertNull(foundEvent);
        assertNull(foundToken);

    }

    //Add Second Test Case
    @Test
    public void testClearDatabaseTwoPer() throws SQLException {
        User user1 = new User("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m", "1a2b");
        User user2 = new User("susan", "password", "susan@gmail.com", "Susan", "Lacey", "f", "2b1a");
        Person person1 = new Person("2asdfq43", "brandonpaul", "Jerry", "Tooley", "m");
        Person person2 = new Person("2qwefe", "susan", "Tony", "Toke", "f");
        Event event1 = new Event("3afafedw", "brandonpaul", "1a2b", (float) 235.556, (float) 124.556, "United States", "New York", "Birth", 1997);
        Event event2 = new Event("qsdf3rw", "susan", "2b1a", (float) 235.556, (float) 124.556, "United States", "Austin", "Marriage", 1997);
        AuthToken token1 = new AuthToken("123456789", "brandonpaul");
        AuthToken token2 = new AuthToken("987654321", "susan");

        User foundUser1 = null;
        User foundUser2 = null;
        Person foundPerson1 = null;
        Person foundPerson2 = null;
        Event foundEvent1 = null;
        Event foundEvent2 = null;
        AuthToken foundToken1 = null;
        AuthToken foundToken2 = null;

        try {
            userDao.createUser(user1);
            userDao.createUser(user2);
            personDao.createPerson(person1);
            personDao.createPerson(person2);
            eventDao.insert(event1);
            eventDao.insert(event2);
            authTokenDao.createAuthToken(token1);
            authTokenDao.createAuthToken(token2);

            db.clearTables();

            foundUser1 = userDao.getUserByUsername(user1.getUsername());
            foundUser2 = userDao.getUserByUsername(user2.getUsername());
            foundPerson1 = personDao.getPersonByID(person1.getPersonID());
            foundPerson2 = personDao.getPersonByID(person2.getPersonID());
            foundEvent1 = eventDao.find(event1.getEventID());
            foundEvent2 = eventDao.find(event2.getEventID());
            foundToken1 = authTokenDao.findByToken(token1.getAuthToken());
            foundToken2 = authTokenDao.findByToken(token2.getAuthToken());

        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNull(foundUser1);
        assertNull(foundUser2);
        assertNull(foundPerson1);
        assertNull(foundPerson2);
        assertNull(foundEvent1);
        assertNull(foundEvent2);
        assertNull(foundToken1);
        assertNull(foundToken2);

    }
}

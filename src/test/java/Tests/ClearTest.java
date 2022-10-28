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

public class ClearTest {
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
    public void testClearDatabase() throws SQLException {
        User user = new User("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m", "1a2b");
        Person person = new Person("2", "brandonpaul", "Jerry", "Tooley", "m");
        Event event = new Event("3", "susan", "6969", (float) 235.556, (float) 124.556, "United States", "New York", "Birth", 1997);
        AuthToken token = new AuthToken("brandonpaul", "123456789");

        try {
            userDao.createUser(user);
            personDao.createPerson(person);
            eventDao.insert(event);
            authTokenDao.createAuthToken(token);

            db.clearTables();

            User foundUser = null;
            Person foundPerson = null;
            Event foundEvent = null;
            AuthToken foundToken = null;

            foundUser = userDao.getUserByUsername(user.getUsername());
            foundPerson = personDao.getPersonByID(person.getPersonID());
            foundEvent = eventDao.find(event.getEventID());
            foundToken = authTokenDao.findByToken(token.getAuthToken());

            assertNull(foundUser);
            assertNull(foundPerson);
            assertNull(foundEvent);
            assertNull(foundToken);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    //Negative Test Case
}

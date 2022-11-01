package Tests;

import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.EventDao;
import DataAccess.UserDao;
import Model.Event;
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

public class EventDaoTest {

    private static Database db;
    private EventDao eventDao;
    private UserDao userDao;

    @BeforeAll
    public static void openDatabase() {
        db = new Database();
    }

    @BeforeEach
    public void setUp() throws DataAccessException, SQLException {

        Connection conn = db.openConnection();
        eventDao = new EventDao(conn);
        userDao = new UserDao(conn);

    }

    @AfterEach
    public void closeDatabase() throws DataAccessException {
        db.closeConnection(false);
    }

    @Test
    //This functions for both Positive Register and Find Functions
    public void createEventTest() {
        Event addEvent1 = new Event("1", "susan", "1234", (float) 13.45, (float) 456.24, "United States", "Denver", "Birth", 1999);

        Event foundEvent1 = null;
        try {
            eventDao.insert(addEvent1);
            foundEvent1 = eventDao.find(addEvent1.getEventID());
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNotNull(foundEvent1);
        assertEquals(addEvent1.getEventID(), foundEvent1.getEventID());
        assertEquals(addEvent1.getAssociatedUsername(), foundEvent1.getAssociatedUsername());
        assertEquals(addEvent1.getPersonID(), foundEvent1.getPersonID());
    }

    @Test
    public void testAddAlreadyExistingEvent() throws SQLException {
        Event addEvent1 = new Event("1", "susan", "1234", (float) 13.45, (float) 456.24, "United States", "Denver", "Birth", 1999);
        try {
            eventDao.insert(addEvent1);
        } catch (DataAccessException ex) {
            System.out.println(ex);
        }

        assertThrows(DataAccessException.class, () -> eventDao.insert(addEvent1));
    }

    @Test
    public void findBadIDTest() {
        Event addEvent1 = new Event("1", "susan", "1234", (float) 13.45, (float) 456.24, "United States", "Denver", "Birth", 1999);

        Event foundEvent1 = null;
        try {
            eventDao.insert(addEvent1);
            foundEvent1 = eventDao.find("wrongID");
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNull(foundEvent1);
    }

    @Test
    public void connectedToUserTest() {
        User newUser = new User("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m", "1234");
        Event addEvent1 = new Event("1", "brandonpaul", "234r4", (float) 13.45, (float) 456.24, "United States", "Denver", "Birth", 1999);

        boolean isConnected = false;
        try {
            userDao.createUser(newUser);
            eventDao.insert(addEvent1);

            isConnected = eventDao.connectedToUser(newUser.getUsername(), addEvent1.getEventID());

        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(isConnected);
    }

    @Test
    public void notConnectedToUserTest() {
        User newUser = new User("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m", "1234");
        Event addEvent1 = new Event("1", "otherperson", "23r4", (float) 13.45, (float) 456.24, "United States", "Denver", "Birth", 1999);

        boolean isConnected = true;
        try {
            userDao.createUser(newUser);
            eventDao.insert(addEvent1);

            isConnected = eventDao.connectedToUser(newUser.getUsername(), addEvent1.getEventID());

        } catch (Exception e) {
            e.printStackTrace();
        }

        assertFalse(isConnected);
    }

    @Test
    public void find1ForUser() {
        User user = new User("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m", "1");

        Event addEvent1 = new Event("1", "brandonpaul", "1234", (float) 13.45, (float) 456.24, "United States", "Denver", "Birth", 1999);

        ArrayList<Event> userEvents = new ArrayList<>();
        try {
            userDao.createUser(user);
            eventDao.insert(addEvent1);

            userEvents = eventDao.findForUser(user.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }

        int numEvents = 0;
        for (Event event : userEvents) {
            numEvents++;

            assertEquals(event.getAssociatedUsername(), user.getUsername());
        }

        assertEquals(numEvents, 1);
    }

    @Test
    public void find2ForUser() {
        User user = new User("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m", "1");

        Event addEvent1 = new Event("1", "brandonpaul", "1234", (float) 13.45, (float) 456.24, "United States", "Denver", "Birth", 1999);
        Event addEvent2 = new Event("2", "brandonpaul", "1234", (float) 34.345, (float) 134.546, "United States", "Pittsburgh", "Wedding", 2016);
        Event addEvent3 = new Event("3", "susan", "6969", (float) 235.556, (float) 124.556, "United States", "New York", "Birth", 1997);

        ArrayList<Event> userEvents = new ArrayList<>();
        try {
            userDao.createUser(user);
            eventDao.insert(addEvent1);
            eventDao.insert(addEvent2);
            eventDao.insert(addEvent3);

            userEvents = eventDao.findForUser(user.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }

        int numEvents = 0;
        for (Event event : userEvents) {
            numEvents++;

            assertEquals(event.getAssociatedUsername(), user.getUsername());
        }

        assertEquals(numEvents, 2);
    }

    @Test
    public void delete1EventTest() {
        Event addEvent1 = new Event("1", "brandonpaul", "1234", (float) 13.45, (float) 456.24, "United States", "Denver", "Birth", 1999);

        try {
            eventDao.insert(addEvent1);
        } catch (DataAccessException ex) {
            System.out.println(ex);
        }

        try {
            eventDao.deleteEvent(addEvent1.getEventID());
        } catch (DataAccessException ex) {
            System.out.println(ex);
        }

        Event foundEvent1 = null;

        try {
            foundEvent1 = eventDao.find(addEvent1.getEventID());
        } catch (DataAccessException ex) {
            System.out.println(ex);
        }

        assertNull(foundEvent1);
    }

    @Test
    public void delete3EventTest() {
        Event addEvent1 = new Event("1", "brandonpaul", "1234", (float) 13.45, (float) 456.24, "United States", "Denver", "Birth", 1999);
        Event addEvent2 = new Event("2", "brandonpaul", "1234", (float) 34.345, (float) 134.546, "United States", "Pittsburgh", "Wedding", 2016);
        Event addEvent3 = new Event("3", "susan", "6969", (float) 235.556, (float) 124.556, "United States", "New York", "Birth", 1997);

        try {
            eventDao.insert(addEvent1);
            eventDao.insert(addEvent2);
            eventDao.insert(addEvent3);
        } catch (DataAccessException ex) {
            System.out.println(ex);
        }

        try {
            eventDao.deleteEvent(addEvent1.getEventID());
            eventDao.deleteEvent(addEvent2.getEventID());
            eventDao.deleteEvent(addEvent3.getEventID());
        } catch (DataAccessException ex) {
            System.out.println(ex);
        }

        Event foundEvent1 = null;
        Event foundEvent2 = null;
        Event foundEvent3 = null;

        try {
            foundEvent1 = eventDao.find(addEvent1.getEventID());
            foundEvent2 = eventDao.find(addEvent2.getEventID());
            foundEvent3 = eventDao.find(addEvent3.getEventID());
        } catch (DataAccessException ex) {
            System.out.println(ex);
        }

        assertNull(foundEvent1);
        assertNull(foundEvent2);
        assertNull(foundEvent3);

    }


}

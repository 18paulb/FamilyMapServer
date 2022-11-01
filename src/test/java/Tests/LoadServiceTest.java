package Tests;

import DataAccess.*;
import Model.Event;
import Model.Person;
import Model.User;
import Request.LoadRequest;
import Result.LoadResult;
import Service.LoadService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


public class LoadServiceTest {
    private static Database db;
    private UserDao userDao;
    private PersonDao personDao;
    private EventDao eventDao;

    @BeforeAll
    public static void openDatabase() {
        db = new Database();
    }

    @BeforeEach
    public void setUp() throws DataAccessException, SQLException {

    }

    @AfterEach
    public void closeDatabase() throws DataAccessException {
        //Clears database of user
        db.openConnection();
        db.clearTables();
        db.closeConnection(true);
    }

    //Positive Test
    @Test
    public void loadTest() {
        User user1 = new User("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m", "1234");

        User[] users = new User[1];
        users[0] = user1;

        Person person1 = new Person("4321", "susan", "Tom", "Sawyer", "m");
        Person person2 = new Person("2", "brandonpaul", "Jerry", "Tooley", "m");
        Person person3 = new Person("3", "otheruser", "Paul", "McCartney", "m");

        Person[] persons = new Person[3];
        persons[0] = person1;
        persons[1] = person2;
        persons[2] = person3;

        Event event1 = new Event("1", "susan", "1234", (float) 13.45, (float) 456.24, "United States", "Denver", "Birth", 1999);
        Event event2 = new Event("2", "susan", "1234", (float) 34.345, (float) 134.546, "United States", "Pittsburgh", "Wedding", 2016);
        Event event3 = new Event("3", "susan", "6969", (float) 235.556, (float) 124.556, "United States", "New York", "Birth", 1997);

        Event[] events = new Event[3];
        events[0] = event1;
        events[1] = event2;
        events[2] = event3;

        LoadRequest request = new LoadRequest(users, persons, events);

        User foundUser1 = null;

        Person foundPerson1 = null;
        Person foundPerson2 = null;
        Person foundPerson3 = null;

        Event foundEvent1 = null;
        Event foundEvent2 = null;
        Event foundEvent3 = null;

        LoadResult result = null;

        try {

            result = LoadService.loadResponse(request);

            Connection conn = db.openConnection();

            userDao = new UserDao(conn);
            personDao = new PersonDao(conn);
            eventDao = new EventDao(conn);

            foundUser1 = userDao.getUserByUsername(user1.getUsername());

            foundPerson1 = personDao.getPersonByID(person1.getPersonID());
            foundPerson2 = personDao.getPersonByID(person2.getPersonID());
            foundPerson3 = personDao.getPersonByID(person3.getPersonID());

            foundEvent1 = eventDao.find(event1.getEventID());
            foundEvent2 = eventDao.find(event2.getEventID());
            foundEvent3 = eventDao.find(event3.getEventID());

            db.closeConnection(true);

        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(result.isSuccess());

        assertNotNull(foundUser1);

        assertNotNull(foundPerson1);
        assertNotNull(foundPerson2);
        assertNotNull(foundPerson3);

        assertNotNull(foundEvent1);
        assertNotNull(foundEvent2);
        assertNotNull(foundEvent3);
    }

    //Negative Test
    @Test
    public void loadMultipleOfSameIDTest() {
        User user1 = new User("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m", "1234");

        User[] users = new User[1];
        users[0] = user1;

        Person person1 = new Person("2", "susan", "Tom", "Sawyer", "m");
        Person person2 = new Person("2", "brandonpaul", "Jerry", "Tooley", "m");
        Person person3 = new Person("2", "otheruser", "Paul", "McCartney", "m");

        Person[] persons = new Person[3];
        persons[0] = person1;
        persons[1] = person2;
        persons[2] = person3;

        Event event1 = new Event("1", "susan", "1234", (float) 13.45, (float) 456.24, "United States", "Denver", "Birth", 1999);
        Event event2 = new Event("1", "susan", "1234", (float) 34.345, (float) 134.546, "United States", "Pittsburgh", "Wedding", 2016);
        Event event3 = new Event("1", "susan", "6969", (float) 235.556, (float) 124.556, "United States", "New York", "Birth", 1997);

        Event[] events = new Event[3];
        events[0] = event1;
        events[1] = event2;
        events[2] = event3;

        LoadRequest request = new LoadRequest(users, persons, events);

        LoadResult result = null;

        try {

            result = LoadService.loadResponse(request);

        } catch (Exception e) {
            e.printStackTrace();
        }

        assertFalse(result.isSuccess());
    }
}

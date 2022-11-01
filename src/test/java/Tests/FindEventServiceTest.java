package Tests;

import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.EventDao;
import Model.AuthToken;
import Model.Event;
import Request.RegisterRequest;
import Result.FindEventResult;
import Result.RegisterResult;
import Service.FindEventService;
import Service.RegisterService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class FindEventServiceTest {
    private static Database db;
    private EventDao eventDao;

    @BeforeAll
    public static void openDatabase() {
        db = new Database();
    }

    @BeforeEach
    public void setUp() throws DataAccessException, SQLException {

        db.openConnection();
        db.clearTables();
        db.closeConnection(true);
    }

    @AfterEach
    public void closeDatabase() throws DataAccessException {
        db.openConnection();
        db.clearTables();
        db.closeConnection(true);
    }

    //PositiveTest
    @Test
    public void findEventTest() {
        //Register User and Make Person
        Event event = new Event("1", "brandonpaul", "1234", (float) 13.45, (float) 456.24, "United States", "Denver", "Birth", 1999);

        FindEventResult findResult = null;
        try {
            RegisterRequest request = new RegisterRequest("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m", "1a2b");
            RegisterResult result = RegisterService.register(request);

            AuthToken token = new AuthToken(result.getAuthtoken(), result.getUsername());

            Connection conn = db.openConnection();
            eventDao = new EventDao(conn);
            eventDao.insert(event);
            db.closeConnection(true);

            //Find the person
            findResult = FindEventService.eventResponse(token.getAuthtoken(), event.getEventID());

        } catch (Exception e) {
            e.printStackTrace();
        }

        Event foundEvent = findResult.getEvent();

        assertNotNull(findResult);
        assertEquals(foundEvent.getEventID(), event.getEventID());
        assertEquals(foundEvent.getAssociatedUsername(), event.getAssociatedUsername());
        assertEquals(foundEvent.getPersonID(), event.getPersonID());
        assertEquals(foundEvent.getLatitude(), event.getLatitude());
        assertEquals(foundEvent.getLongitude(), event.getLongitude());
        assertEquals(foundEvent.getCountry(), event.getCountry());
        assertEquals(foundEvent.getCity(), event.getCity());
        assertEquals(foundEvent.getEventType(), event.getEventType());
        assertEquals(foundEvent.getYear(), event.getYear());

    }

    //Negative Test Case
    @Test
    public void eventNotAttachedToUserTest() {

        Event event = new Event("1", "susan", "1234", (float) 13.45, (float) 456.24, "United States", "Denver", "Birth", 1999);

        FindEventResult findResult = null;
        try {
            //Creates Person and User
            RegisterRequest request = new RegisterRequest("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m", "1a2b");
            RegisterResult result = RegisterService.register(request);

            AuthToken token = new AuthToken(result.getAuthtoken(), result.getUsername());

            Connection conn = db.openConnection();
            eventDao = new EventDao(conn);
            eventDao.insert(event);
            db.closeConnection(true);

            findResult = FindEventService.eventResponse(token.getAuthtoken(), event.getEventID());

        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNotNull(findResult);
        assertNull(findResult.getEvent());
    }
}

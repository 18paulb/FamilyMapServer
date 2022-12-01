package Tests;

import DataAccess.*;
import Model.AuthToken;
import Request.RegisterRequest;
import Result.GetAllEventResult;
import Result.RegisterResult;
import Service.GetAllEventService;
import Service.RegisterService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GetAllEventServiceTest {
    private static Database db;
    private UserDao userDao;
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
    @Test
    public void getAllEventTest() throws DataAccessException, SQLException {

        GetAllEventResult findResult = null;
        RegisterResult registerResult = null;
        int numEvents = 0;
        try {
            RegisterRequest request = new RegisterRequest("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m");
            registerResult = RegisterService.register(request);

            AuthToken token = new AuthToken(registerResult.getAuthtoken(), registerResult.getUsername());

            //Find the person
            findResult = GetAllEventService.eventResponse(token.getAuthtoken());


            Connection conn = db.openConnection();
            eventDao = new EventDao(conn);
            numEvents = eventDao.findForUser(registerResult.getUsername()).size();
            db.closeConnection(true);

        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(numEvents, 91);
        assertTrue(findResult.isSuccess());
    }


    //Negative Test
    @Test
    public void invalidAuthtokenTest() {

        GetAllEventResult findResult = null;
        try {
            //Creates Person and User
            RegisterRequest request = new RegisterRequest("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m", "1a2b");
            RegisterResult result = RegisterService.register(request);

            AuthToken token = new AuthToken(result.getAuthtoken(), result.getUsername());

            findResult = GetAllEventService.eventResponse("wrongToken");

        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNotNull(findResult);
        assertFalse(findResult.isSuccess());
    }
}

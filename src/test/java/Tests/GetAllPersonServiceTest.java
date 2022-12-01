package Tests;

import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.PersonDao;
import DataAccess.UserDao;
import Model.AuthToken;
import Request.RegisterRequest;
import Result.GetAllPersonResult;
import Result.RegisterResult;
import Service.GetAllPersonService;
import Service.RegisterService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class GetAllPersonServiceTest {

    private static Database db;
    private UserDao userDao;
    private PersonDao personDao;

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
    public void getAllPersonTest() throws DataAccessException, SQLException {

        GetAllPersonResult findResult = null;
        RegisterResult registerResult = null;
        int numPersons = 0;
        try {
            RegisterRequest request = new RegisterRequest("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m");
            registerResult = RegisterService.register(request);

            AuthToken token = new AuthToken(registerResult.getAuthtoken(), registerResult.getUsername());

            //Find the person
            findResult = GetAllPersonService.personResponse(token.getAuthtoken());


            Connection conn = db.openConnection();
            personDao = new PersonDao(conn);
            numPersons = personDao.getTreeOfUser(registerResult.getUsername()).size();
            db.closeConnection(true);

        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(numPersons, 31);
        assertTrue(findResult.isSuccess());

    }


    //Negative Test
    @Test
    public void invalidAuthtokenTest() {
        GetAllPersonResult findResult = null;
        try {
            //Creates Person and User
            RegisterRequest request = new RegisterRequest("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m", "1a2b");
            RegisterResult result = RegisterService.register(request);

            AuthToken token = new AuthToken(result.getAuthtoken(), result.getUsername());

            findResult = GetAllPersonService.personResponse("wrongToken");

        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNotNull(findResult);
        assertFalse(findResult.isSuccess());
    }
}

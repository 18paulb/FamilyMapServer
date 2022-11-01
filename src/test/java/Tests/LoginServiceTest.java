package Tests;

import DataAccess.AuthTokenDao;
import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.UserDao;
import Model.AuthToken;
import Model.User;
import Request.LoginRequest;
import Request.RegisterRequest;
import Result.LoginResult;
import Service.LoginService;
import Service.RegisterService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class LoginServiceTest {
    private static Database db;
    private UserDao userDao;
    private AuthTokenDao authTokenDao;

    private User user;

    @BeforeAll
    public static void openDatabase() {
        db = new Database();
    }

    @BeforeEach
    public void setUp() throws DataAccessException, SQLException {

        //Adds a user to databse
        user = new User("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m", "1a2b");
        RegisterRequest request = new RegisterRequest(user.getUsername(), user.getPassword(), user.getEmail(), user.getFirstName(),
                user.getLastName(), user.getGender(), user.getPersonID());

        RegisterService.register(request);

        //Opens database for test
        Connection conn = db.openConnection();
        userDao = new UserDao(conn);
        authTokenDao = new AuthTokenDao(conn);
    }

    @AfterEach
    public void closeDatabase() throws DataAccessException {
        //Clears database of user
        db.clearTables();
        db.closeConnection(true);
    }

    //Positive Test
    @Test
    public void loginTest() throws SQLException, DataAccessException {
        AuthToken token = null;
        try {

            LoginRequest request = new LoginRequest(user.getUsername(), user.getPassword());

            LoginResult result = LoginService.loginResponse(request);

            //Check to see if AuthToken is attached to user
            token = authTokenDao.find(result.getUsername());

        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNotNull(token);
        assertEquals(token.getUsername(), user.getUsername());
    }

    //Negative Test
    //Login non existing user in
    @Test
    public void loginFailTest() {
        LoginResult result = null;
        try {
            User newUser = new User("jimbob", "password", "jim3@gmail.com", "Jim", "Bob", "m", "asetf4r");

            LoginRequest request = new LoginRequest(newUser.getUsername(), newUser.getPassword());

            result = LoginService.loginResponse(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNotNull(result);
        assertFalse(result.isSuccess());
    }
}

package Tests;

import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.UserDao;
import Model.AuthToken;
import Model.User;
import Request.RegisterRequest;
import Result.RegisterResult;
import Service.RegisterService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class UserRegisterServiceTest {
    private static Database db;
    private UserDao userDao;

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
    }


    //Positive Test Case
    @Test
    public void registerPassTest() throws SQLException {
        User user = new User("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m", "1a2b");
        User tmpUser = null;

        try {
            RegisterRequest request = new RegisterRequest(user.getUsername(), user.getPassword(), user.getEmail(), user.getFirstName(),
                    user.getLastName(), user.getGender(), user.getPersonID());

            RegisterService.registerResponse(request);

            Connection conn = db.openConnection();
            userDao = new UserDao(conn);
            tmpUser = userDao.getUserByUsername(user.getUsername());
            db.closeConnection(true);


        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNotNull(tmpUser);
        assertEquals(user.getUsername(), tmpUser.getUsername());
        assertEquals(user.getPassword(), tmpUser.getPassword());
        assertEquals(user.getEmail(), tmpUser.getEmail());
        assertEquals(user.getFirstName(), tmpUser.getFirstName());
        assertEquals(user.getLastName(), tmpUser.getLastName());
        assertEquals(user.getGender(), tmpUser.getGender());
        assertEquals(user.getPersonID(), tmpUser.getPersonID());
    }

    //Negative Test Case
    @Test
    public void registerAddDuplicateUserTest() {
        User user = new User("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m", "1a2b");
        RegisterRequest request = new RegisterRequest(user.getUsername(), user.getPassword(), user.getEmail(), user.getFirstName(),
                user.getLastName(), user.getGender(), user.getPersonID());

        //Adds Initial User to Database
        try {

            RegisterService.registerResponse(request);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Tries to add duplicate here
        RegisterResult result = null;

        try {
            result = RegisterService.registerResponse(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNotNull(result);
        assertFalse(result.isSuccess());
    }

}

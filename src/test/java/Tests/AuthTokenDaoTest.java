package Tests;

import DataAccess.AuthTokenDao;
import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.UserDao;
import Model.AuthToken;
import Model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class AuthTokenDaoTest {
    private static Database db;
    private AuthTokenDao tokenDao;
    private UserDao userDao;

    @BeforeAll
    public static void openDatabase() {
        db = new Database();
    }

    @BeforeEach
    public void setUp() throws DataAccessException, SQLException {

        Connection conn = db.openConnection();
        tokenDao = new AuthTokenDao(conn);
        userDao = new UserDao(conn);

    }

    @AfterEach
    public void closeDatabase() throws DataAccessException {
        db.closeConnection(false);
    }

    @Test
    //Positive Test Case for both Create and Find By Token Functions
    public void createAuthtoken() {
        User newUser = new User("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m", "1234");
        AuthToken token = new AuthToken(newUser.getUsername());

        AuthToken found = null;
        try {
            tokenDao.createAuthToken(token);

            found = tokenDao.findByToken(token.getAuthtoken());
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNotNull(found);
        assertEquals(token.getUsername(), found.getUsername());
        assertEquals(token.getAuthtoken(), found.getAuthtoken());
    }

    @Test
    public void createDuplicateAuthtoken() {
        User newUser = new User("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m", "1234");
        AuthToken token = new AuthToken(newUser.getUsername());

        try {
            tokenDao.createAuthToken(token);

        } catch (Exception e) {
            e.printStackTrace();
        }

        assertThrows(DataAccessException.class, () -> tokenDao.createAuthToken(token));
    }

    @Test
    public void findWrongUsername() {
        User newUser = new User("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m", "1234");
        AuthToken token = new AuthToken(newUser.getUsername());

        AuthToken found = null;
        try {
            tokenDao.createAuthToken(token);

            found = tokenDao.find("sdfe");
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNull(found);
    }

    @Test
    public void findWrongTokenID() {
        User newUser = new User("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m", "1234");
        AuthToken token = new AuthToken(newUser.getUsername());

        AuthToken found = null;
        try {
            tokenDao.createAuthToken(token);

            found = tokenDao.findByToken("sdfe");
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNull(found);
    }
}

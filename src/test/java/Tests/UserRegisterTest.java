package Tests;

import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.UserDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.SQLException;

public class UserRegisterTest {
    private static Database db;
    private UserDao userDao;

    @BeforeAll
    public static void openDatabase() {
        db = new Database();
    }

    @BeforeEach
    public void setUp() throws DataAccessException, SQLException {

        Connection conn = db.openConnection();
        userDao = new UserDao(conn);
    }

    @AfterEach
    public void closeDatabase() throws DataAccessException {
        db.closeConnection(false);
    }


    //Positive Test Case
}

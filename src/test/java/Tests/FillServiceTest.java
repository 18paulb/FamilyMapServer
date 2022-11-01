package Tests;

import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.EventDao;
import DataAccess.UserDao;
import Model.Person;
import Model.User;
import Request.FillRequest;
import Result.FillResult;
import Service.FillService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class FillServiceTest {

    private static Database db;

    @BeforeEach
    public void setUp() throws DataAccessException, SQLException {
        db = new Database();
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

    //Positive Test
    //TODO: Make assertions
    @Test
    public void fillFourGenerations() {
        FillResult result = null;

        try {

            User user = new User("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m", "1234");

            Connection conn = db.openConnection();
            UserDao userDao = new UserDao(conn);
            userDao.createUser(user);

            db.closeConnection(true);

            FillRequest request = new FillRequest(user.getUsername(), 1);
            result = FillService.fillResponse(request);

            if (result.isSuccess()) {
                System.out.println(result.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

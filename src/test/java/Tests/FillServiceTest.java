package Tests;

import DataAccess.*;
import Model.Event;
import Model.Person;
import Model.User;
import Request.FillRequest;
import Request.RegisterRequest;
import Result.FillResult;
import Result.RegisterResult;
import Service.FillService;
import Service.RegisterService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
    @Test
    public void fillAtRegister() throws DataAccessException {

        ArrayList<Person> persons = new ArrayList<>();
        ArrayList<Event> events = new ArrayList<>();
        try {

            User user = new User("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m");

            RegisterRequest request = new RegisterRequest(user.getUsername(), user.getPassword(), user.getEmail(), user.getFirstName(),
                    user.getLastName(), user.getGender());
            RegisterResult result = RegisterService.register(request);

            Connection conn = db.openConnection();
            PersonDao personDao = new PersonDao(conn);
            EventDao eventDao = new EventDao(conn);

            persons = personDao.getTreeOfUser(user.getUsername());
            events = eventDao.findForUser(user.getUsername());

            db.closeConnection(true);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Test 4 Generations Have been created
        assertEquals(persons.size(), 31);
        assertEquals(events.size(), 91);
    }

    @Test
    public void negativeGenerationTest() throws DataAccessException {
        FillResult result = null;

        try {
            User user = new User("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m");

            Connection conn = db.openConnection();
            UserDao userDao = new UserDao(conn);
            userDao.createUser(user);
            db.closeConnection(true);

            FillRequest request = new FillRequest(user.getUsername(), -2);

            result = FillService.fillResponse(request);

        } catch (Exception e) {
            e.printStackTrace();
        }

        assertFalse(result.isSuccess());
    }
}

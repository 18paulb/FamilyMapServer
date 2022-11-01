package Tests;

import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.PersonDao;
import DataAccess.UserDao;
import Model.AuthToken;
import Model.Person;
import Request.RegisterRequest;
import Result.FindPersonResult;
import Result.RegisterResult;
import Service.FindPersonService;
import Service.RegisterService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class FindPersonServiceTest {

    private static Database db;
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
    public void findPersonTest() {
        //Register User and Make Person
        FindPersonResult findResult = null;
        RegisterResult registerResult = null;
        try {

            RegisterRequest request = new RegisterRequest("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m");
            registerResult = RegisterService.register(request);

            AuthToken token = new AuthToken(registerResult.getAuthtoken(), registerResult.getUsername());

            //Find the person
            findResult = FindPersonService.personResponse(token.getAuthtoken(), registerResult.getPersonID());

        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(findResult.isSuccess());
        assertEquals(findResult.getPersonID(), registerResult.getPersonID());
        assertEquals(findResult.getAssociatedUsername(), registerResult.getUsername());

    }

    //Negative Test Case
    @Test
    public void personNotAttachedToUserTest() {

        Person person = new Person("2145", "otheruser", "Jerry", "Tooley", "m");

        FindPersonResult findResult = null;
        try {
            //Creates Person and User
            RegisterRequest request = new RegisterRequest("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m", "1a2b");
            RegisterResult result = RegisterService.register(request);

            AuthToken token = new AuthToken(result.getAuthtoken(), result.getUsername());

            Connection conn = db.openConnection();
            personDao = new PersonDao(conn);
            personDao.createPerson(person);
            db.closeConnection(true);

            findResult = FindPersonService.personResponse(token.getAuthtoken(), person.getPersonID());

        } catch (Exception e) {
            e.printStackTrace();
        }

        assertFalse(findResult.isSuccess());
    }

}

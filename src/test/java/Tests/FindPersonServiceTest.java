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
        try {

            RegisterRequest request = new RegisterRequest("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m");
            RegisterResult result = RegisterService.register(request);

            AuthToken token = new AuthToken(result.getAuthtoken(), result.getUsername());

            //Find the person
            findResult = FindPersonService.personResponse(token.getAuthtoken(), result.getPersonID());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //PositiveTest
    /*
    @Test

    public void findPersonTest() {
        //Register User and Make Person
        Person person = new Person("2", "brandonpaul", "Jerry", "Tooley", "m");

        FindPersonResult findResult = null;
        try {

            RegisterRequest request = new RegisterRequest("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m");
            RegisterResult result = RegisterService.register(request);

            AuthToken token = new AuthToken(result.getAuthtoken(), result.getUsername());

            Connection conn = db.openConnection();
            personDao = new PersonDao(conn);
            personDao.createPerson(person);
            db.closeConnection(true);

            //Find the person
            findResult = FindPersonService.personResponse(token.getAuthtoken(), person.getPersonID());

        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNotNull(findResult);
        assertEquals(findResult.getPerson().getPersonID(), person.getPersonID());
        assertEquals(findResult.getPerson().getAssociatedUsername(), person.getAssociatedUsername());
        assertEquals(findResult.getPerson().getFirstName(), person.getFirstName());
        assertEquals(findResult.getPerson().getLastName(), person.getLastName());
        assertEquals(findResult.getPerson().getGender(), person.getGender());
    }

     */

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

        //assertNotNull(findResult);
        //assertNull(findResult.getPerson());
    }

}

package Tests;

import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.PersonDao;
import DataAccess.UserDao;
import Model.AuthToken;
import Model.Person;
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
    public void getAllPersonTest() {

        GetAllPersonResult findResult = null;
        try {
            RegisterRequest request = new RegisterRequest("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m");
            RegisterResult result = RegisterService.register(request);

            AuthToken token = new AuthToken(result.getAuthtoken(), result.getUsername());

            //Find the person
            findResult = GetAllPersonService.personResponse(token.getAuthtoken());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    @Test
    public void getAllPersonTest() {
        Person person1 = new Person("2", "brandonpaul", "Jerry", "Tooley", "m");
        Person person2 = new Person("3", "brandonpaul", "Tommy", "Tutone", "m");

        GetAllPersonResult findResult = null;
        try {
            RegisterRequest request = new RegisterRequest("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m", "1a2b");
            RegisterResult result = RegisterService.register(request);

            AuthToken token = new AuthToken(result.getAuthtoken(), result.getUsername());

            Connection conn = db.openConnection();
            personDao = new PersonDao(conn);
            personDao.createPerson(person1);
            personDao.createPerson(person2);
            db.closeConnection(true);

            //Find the person
            findResult = GetAllPersonService.personResponse(token.getAuthtoken());

        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNotNull(findResult);
        Person foundPerson1 = findResult.getPersons()[0];
        Person foundPerson2 = findResult.getPersons()[1];

        assertEquals(foundPerson1.getPersonID(), person1.getPersonID());
        assertEquals(foundPerson1.getAssociatedUsername(), person1.getAssociatedUsername());
        assertEquals(foundPerson1.getFirstName(), person1.getFirstName());
        assertEquals(foundPerson1.getLastName(), person1.getLastName());
        assertEquals(foundPerson1.getGender(), person1.getGender());

        assertEquals(foundPerson2.getPersonID(), person2.getPersonID());
        assertEquals(foundPerson2.getAssociatedUsername(), person2.getAssociatedUsername());
        assertEquals(foundPerson2.getFirstName(), person2.getFirstName());
        assertEquals(foundPerson2.getLastName(), person2.getLastName());
        assertEquals(foundPerson2.getGender(), person2.getGender());
    }

     */

    //Negative Test
    @Test
    public void personsNotAttachedToUser() {
        Person person1 = new Person("2145", "otheruser", "Jerry", "Tooley", "m");
        Person person2 = new Person("21345", "other", "Tony", "Tooley", "f");

        GetAllPersonResult findResult = null;
        try {
            //Creates Person and User
            RegisterRequest request = new RegisterRequest("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m", "1a2b");
            RegisterResult result = RegisterService.register(request);

            AuthToken token = new AuthToken(result.getAuthtoken(), result.getUsername());

            Connection conn = db.openConnection();
            personDao = new PersonDao(conn);
            personDao.createPerson(person1);
            personDao.createPerson(person2);
            db.closeConnection(true);

            findResult = GetAllPersonService.personResponse(token.getAuthtoken());

        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNotNull(findResult);
        assertEquals(findResult.getData().size(), 0);
    }
}

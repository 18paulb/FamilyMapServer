package Tests;

import DataAccess.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.*;

public class MyTest {

    public static void main(String args[]) throws SQLException, DataAccessException {
        Database db = new Database();
        //Opens Connection
        Connection conn = null;
        try {
            conn = db.openConnection();
        } catch (Exception e) {
            System.out.println(e);
        }

        EventDao eventDao = new EventDao(conn);
        PersonDao personDao = new PersonDao(conn);
        UserDao userDao = new UserDao(conn);
/*
        User user = new User("brandonpaul", "password", "bjpaul99@gmail.com", "Brandon", "Paul", "m", "1234");

        try {
            userDao.createUser(user);
        } catch (DataAccessException ex) {
            System.out.println(ex);
        }
*/

/*
        //Person person1 = new Person("4321", "susan", "Tom", "Sawyer", "m");
        //Person person2 = new Person("2", "brandonpaul", "Jerry", "Tooley", "m");
        //Person person3 = new Person("3", "otheruser", "Paul", "McCartney", "m");

        try {
            //personDao.createPerson(person1);
            //personDao.createPerson(person2);
            //personDao.createPerson(person3);
        } catch (DataAccessException ex) {
            System.out.println(ex);
        }
*/

/*
        try {
            List<Event> foundEvents = eventDao.findForUser("brandonpaul");
            System.out.println(foundEvents);
        } catch (DataAccessException ex) {
            System.out.println(ex);
        }
*/

        Event addEvent1 = new Event("1", "susan", "1234", (float) 13.45, (float) 456.24, "United States", "Denver", "Birth", 1999);
        Event addEvent2 = new Event("2", "susan", "1234", (float) 34.345, (float) 134.546, "United States", "Pittsburgh", "Wedding", 2016);
        Event addEvent3 = new Event("3", "susan", "6969", (float) 235.556, (float) 124.556, "United States", "New York", "Birth", 1997);

        try {
            eventDao.insert(addEvent1);
            eventDao.insert(addEvent2);
            eventDao.insert(addEvent3);
        } catch (DataAccessException ex) {
            System.out.println(ex);
        }


        //Close the connection
        try {
            db.closeConnection(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}

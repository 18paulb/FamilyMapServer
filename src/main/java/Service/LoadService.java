package Service;

import DataAccess.*;
import Model.Event;
import Model.Person;
import Model.User;
import Request.LoadRequest;
import Result.LoadResult;

import java.sql.Connection;

/**
 * Uses Dao to clear database and add new info
 */
public class LoadService {

    /**
     * Returns a Load Result from Login Request
     * @param request - The LoadRequest
     * @return - The Load Result Object
     * @throws DataAccessException
     */
    public static LoadResult loadResponse(LoadRequest request) throws DataAccessException {
        LoadResult result = null;

        Database db = new Database();


        try {
            Connection conn = db.openConnection();
            //Clears database
            db.clearTables();
            UserDao userDao = new UserDao(conn);
            PersonDao personDao = new PersonDao(conn);
            EventDao eventDao = new EventDao(conn);

            User[] users = request.getUsers();
            Person[] persons = request.getPersons();
            Event[] events = request.getEvents();

            for (User user : users) {
                userDao.createUser(user);
            }

            for (Person person : persons) {
                personDao.createPerson(person);
            }

            for (Event event : events) {
                eventDao.insert(event);
            }

            db.closeConnection(true);

            result = new LoadResult("Successfully added " + users.length + " users, " + persons.length + " persons, and "
                    + events.length + " events to the database.", true);

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            db.closeConnection(false);

            result = new LoadResult("Error: [" + e.toString() + "]", false);

            return result;
        }

    }
}

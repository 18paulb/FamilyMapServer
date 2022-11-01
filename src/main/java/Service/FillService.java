package Service;

import Data.Generation;
import DataAccess.*;
import Model.Event;
import Model.Person;
import Model.User;
import Request.FillRequest;
import Request.LoginRequest;
import Result.FillResult;
import Result.LoginResult;

import java.sql.Connection;
import java.util.List;

/**
 * Uses Dao classes to interact with database and add info
 */
public class FillService {
    /**
     * Returns a Fill Result from Fill Request
     * @return - The Fill Result Object
     * @throws DataAccessException
     */
    public static FillResult fillResponse(int numGenerations, String username) throws DataAccessException {
        FillResult result = new FillResult();

        Database db = new Database();

        if (numGenerations < 0) {
            result = new FillResult("Error: [Generation is less than 0]", false);
            return result;
        }

        try {

            Connection conn = db.openConnection();

            UserDao userDao = new UserDao(conn);
            PersonDao personDao = new PersonDao(conn);
            EventDao eventDao = new EventDao(conn);

            User foundUser = userDao.getUserByUsername(username);
            if (foundUser == null) {
                result = new FillResult("Error: [User does not exist]", false);
                db.closeConnection(false);
                return result;
            }

            //Clear any data associated with User
            //Clear all Persons associated with User
            List<Person> userPersons = personDao.getTreeOfUser(username);
            for (Person person : userPersons) {
                personDao.deletePerson(person.getPersonID());
            }

            //Clear events associated with User
            List<Event> events = eventDao.findForUser(username);
            for (Event event : events) {
                eventDao.deleteEvent(event.getEventID());
            }

            //Generate Data
            Generation family = new Generation();
            family.generateFamily(numGenerations, foundUser);

            //Create Persons
            for (Person person : family.getPersons()) {
                personDao.createPerson(person);
            }

            //Create Events
            for (Event event : family.getEvents()) {
                eventDao.insert(event);
            }

            db.closeConnection(true);

            result = new FillResult("Successfully added " + family.getPersons().size() + " persons and " + family.getEvents().size()
                    + " events to the database.", true);

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            result = new FillResult(e.toString(), false);
            db.closeConnection(false);

            return result;
        }
    }
}

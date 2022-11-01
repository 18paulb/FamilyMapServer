package Service;

import DataAccess.*;
import Model.AuthToken;
import Model.Person;
import Model.User;
import Result.GetAllPersonResult;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Uses Dao classes to find all Events attached to User
 */
public class GetAllPersonService {
    /**
     * Returns a GetAllPerson Result from GetAllPerson Request
     * @param authToken - Authtoken of the user
     * @return - The GetAllPerson Result Object
     * @throws DataAccessException
     */
    public static GetAllPersonResult personResponse(String authToken) throws DataAccessException {

        GetAllPersonResult result = new GetAllPersonResult();

        Database db = new Database();

        try {

            Connection conn = db.openConnection();

            AuthTokenDao authTokenDao = new AuthTokenDao(conn);
            UserDao userDao = new UserDao(conn);
            PersonDao personDao = new PersonDao(conn);

            AuthToken token = authTokenDao.findByToken(authToken);

            User user = userDao.getUserByUsername(token.getUsername());

            ArrayList<Person> connectedPersons = personDao.getTreeOfUser(user.getUsername());

            db.closeConnection(true);

            result = new GetAllPersonResult(connectedPersons, true);

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            db.closeConnection(false);
            result = new GetAllPersonResult("Error: [" + e.toString() + "]", false);
            return result;
        }
    }
}

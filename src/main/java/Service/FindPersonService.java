package Service;

import DataAccess.*;
import Model.AuthToken;
import Model.Person;
import Model.User;
import Request.FindPersonRequest;
import Result.FindPersonResult;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Uses Dao classes to find a Person
 */
public class FindPersonService {
    /**
     * Returns a FindPerson Result from FindPerson Request
     * @param authToken - The authToken being used for authentification
     * @param personID - The id of the person being searched for
     * @return - The FindPerson Result Object
     * @throws DataAccessException
     */
    public static FindPersonResult personResponse(String authToken, String personID) throws DataAccessException, SQLException {
        FindPersonResult result = new FindPersonResult();

        Database db = new Database();

        try {

            Connection conn = db.openConnection();

            AuthTokenDao tokenDao = new AuthTokenDao(conn);
            PersonDao personDao = new PersonDao(conn);
            UserDao userDao = new UserDao(conn);

            //Finds the token
            AuthToken token = tokenDao.findByToken(authToken);

            if (token != null) {
                System.out.println("Works");
            } else {
                System.out.println("Error in finding token");
                return result;
            }

            //Finds user from Token's associated username
            User user = userDao.getUserByUsername(token.getUsername());

            if (personDao.connectedToUser(user.getUsername(), personID)) {
                result = new FindPersonResult(personDao.getPersonByID(personID), true);
            } else {
                result = new FindPersonResult("Not connected to user", false);
            }

            db.closeConnection(true);

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            db.closeConnection(false);

            result = new FindPersonResult(e.toString(), false);

            return result;
        }
    }
}

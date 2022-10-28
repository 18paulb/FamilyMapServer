package Service;

import DataAccess.*;
import Model.AuthToken;
import Model.User;
import Request.ClearRequest;
import Request.FindEventRequest;
import Result.ClearResult;
import Result.FindEventResult;
import Result.FindPersonResult;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Uses Dao classes to find an Event
 */
public class FindEventService {
    /**
     * Returns a FindEvent Result from FindEvent Request
     * @param authToken - The authToken of a user
     * @param eventID - ID of the event to be found
     * @return - The FindEvent Result Object
     * @throws DataAccessException
     */
    public static FindEventResult eventResponse(String authToken, String eventID) throws DataAccessException, SQLException {
        FindEventResult result = new FindEventResult();

        Database db = new Database();

        try {

            Connection conn = db.openConnection();

            AuthTokenDao tokenDao = new AuthTokenDao(conn);
            EventDao eventDao = new EventDao(conn);
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

            if (eventDao.connectedToUser(user.getUsername(), eventID)) {
                result = new FindEventResult(eventDao.find(eventID), true);
            }

            db.closeConnection(true);

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            db.closeConnection(false);

            result = new FindEventResult(e.toString(), false);

            return result;
        }
    }
}

package Service;

import DataAccess.*;
import Model.AuthToken;
import Model.User;
import Result.FindEventResult;

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
                result = new FindEventResult("Error: [Could not find authToken]", false);
                db.closeConnection(false);
                return result;
            }

            //Finds user from Token's associated username
            User user = userDao.getUserByUsername(token.getUsername());

            if (eventDao.connectedToUser(user.getUsername(), eventID)) {
                result = new FindEventResult(eventDao.find(eventID), true);
            } else {
                result = new FindEventResult("Error: [No event with that ID connected to user]", false);
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

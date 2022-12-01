package Service;

import DataAccess.*;
import Model.AuthToken;
import Model.Event;
import Model.User;
import Result.GetAllEventResult;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Uses Dao classes to find all Events attached to User
 */
public class GetAllEventService {
    /**
     * Returns a GetAllEvent Result from GetAllEvent Request
     * @param authToken - AuthToken of the current User
     * @return - The GetAllEvent Result Object
     * @throws DataAccessException
     */
    public static GetAllEventResult eventResponse(String authToken) throws DataAccessException, SQLException {
        GetAllEventResult result = new GetAllEventResult();

        Database db = new Database();

        try {

            Connection conn = db.openConnection();

            AuthTokenDao authTokenDao = new AuthTokenDao(conn);
            UserDao userDao = new UserDao(conn);
            EventDao eventDao = new EventDao(conn);

            AuthToken token = authTokenDao.findByToken(authToken);

            User user = userDao.getUserByUsername(token.getUsername());

            ArrayList<Event> listEvents = eventDao.findForUser(user.getUsername());

            db.closeConnection(true);

            result = new GetAllEventResult(listEvents, true);

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            db.closeConnection(false);
            result = new GetAllEventResult("Error: [" + e.toString() + "]", false);
            return result;
        }
    }
}

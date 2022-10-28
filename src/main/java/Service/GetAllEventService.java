package Service;

import DataAccess.*;
import Model.AuthToken;
import Model.Event;
import Model.Person;
import Model.User;
import Request.GetAllEventRequest;
import Result.GetAllEventResult;
import Result.GetAllPersonResult;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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

            List<Event> connectedEvents = eventDao.findForUser(user.getUsername());

            result = new GetAllEventResult(connectedEvents, true);

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            result = new GetAllEventResult(e.toString(), false);
            return result;
        }
    }
}

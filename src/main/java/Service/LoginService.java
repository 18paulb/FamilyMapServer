package Service;

import DataAccess.AuthTokenDao;
import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.UserDao;
import Model.AuthToken;
import Model.User;
import Request.LoginRequest;
import Result.LoginResult;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Uses Dao classes to log in a User
 */
public class LoginService {

    /**
     * Returns a Login Result from Login Request
     * @param request - The LoginRequest
     * @return - The Login Result Object
     * @throws DataAccessException
     */
    public static LoginResult loginResponse(LoginRequest request) throws DataAccessException, SQLException {
        LoginResult result = new LoginResult();

        Database db = new Database();


        try {
            Connection conn = db.openConnection();

            UserDao userDao = new UserDao(conn);
            AuthTokenDao tokenDao = new AuthTokenDao(conn);

            //TODO: Safeguard against nullptr exceptions better
            User foundUser = null;
            AuthToken newToken = null;
            if (userDao.validate(request.getUsername(), request.getPassword())) {
                foundUser = userDao.getUserByUsername(request.getUsername());
                newToken = new AuthToken(foundUser.getUsername());
                tokenDao.createAuthToken(newToken);
            }

            db.closeConnection(true);

            if (foundUser != null) {
                result = new LoginResult(newToken.getAuthtoken(), foundUser.getUsername(), foundUser.getPersonID(), true);
                return result;
            }
            else {
                System.out.println("Something is going wrong");
                result = new LoginResult("Error: [Failure in logging in, User not found]", false);
                return result;
            }


        } catch (Exception ex) {
            ex.printStackTrace();
            db.closeConnection(false);

            result = new LoginResult("Error: [" + ex.toString() + "]", false);
            return result;
        }
    }

}

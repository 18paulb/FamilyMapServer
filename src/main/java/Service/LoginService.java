package Service;

import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.UserDao;
import Model.User;
import Request.LoginRequest;
import Result.LoginResult;

import java.sql.Connection;

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
    public static LoginResult loginResponse(LoginRequest request) throws DataAccessException {
        LoginResult result = new LoginResult();

        Database db = new Database();

        try {
            Connection conn = db.openConnection();

            UserDao userDao = new UserDao(conn);

            User foundUser = null;
            foundUser = userDao.getUserByUsername(request.getUsername());


            if (foundUser != null) {
                if (!foundUser.getPassword().equals(request.getPassword())) {
                    System.out.println(foundUser.getPassword());
                    System.out.println(request.getPassword());
                    foundUser = null;
                }
            }

            db.closeConnection(true);

            if (foundUser != null) {
                result = new LoginResult("authToken", foundUser.getUsername(), foundUser.getPersonID(), "Success", true);
                return result;
            } else {
                System.out.println("Something is going wrong");
            }

        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();

            db.closeConnection(false);

            result = new LoginResult("Failure", false);
            return result;
        }

        return result;
    }

}

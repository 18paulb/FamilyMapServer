package Service;

import DataAccess.AuthTokenDao;
import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.UserDao;
import Model.AuthToken;
import Model.Person;
import Model.User;
import Request.RegisterRequest;
import Result.RegisterResult;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Uses Dao classes to register a new User
 */
public class RegisterService {
    /**
     * Returns a Register Result from Register Request
     * @param request - The RegisterRequest
     * @return - The Register Result Object
     * @throws DataAccessException
     */
    public static RegisterResult register(RegisterRequest request) throws DataAccessException, SQLException {
        RegisterResult result = new RegisterResult();

        Database db = new Database();

        try {
            Connection conn = db.openConnection();

            UserDao userDao = new UserDao(conn);
            AuthTokenDao tokenDao = new AuthTokenDao(conn);

            User newUser = new User(request.getUsername(), request.getPassword(), request.getEmail(), request.getFirstName(),
                    request.getLastName(), request.getGender());

            System.out.println("Creating New User");

            userDao.createUser(newUser);

            AuthToken token = new AuthToken(newUser.getUsername());

            System.out.println("Creating new AuthToken");

            tokenDao.createAuthToken(token);

            db.closeConnection(true);

            //TODO: Generate Family Data
            //Generate 4 Gens of Family
            FillService.fillResponse(4, newUser.getUsername());

            System.out.println("Returning result with token: " + token.getAuthtoken() + ", username: " + newUser.getUsername() + " personID: " + newUser.getPersonID());

            result = new RegisterResult(token.getAuthtoken(), newUser.getUsername(), newUser.getPersonID(), true);
            return result;

        } catch (Exception e) {
            e.printStackTrace();

            db.closeConnection(false);

            result = new RegisterResult(e.toString(), false);
            return result;
        }
    }
}

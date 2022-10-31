package Service;

import DataAccess.AuthTokenDao;
import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.UserDao;
import Model.AuthToken;
import Model.User;
import Request.LoginRequest;
import Request.RegisterRequest;
import Result.LoginResult;
import Result.RegisterResult;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.UUID;

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
    public static RegisterResult registerResponse(RegisterRequest request) throws DataAccessException, SQLException {
        RegisterResult result = new RegisterResult();

        Database db = new Database();

        try {
            Connection conn = db.openConnection();

            UserDao userDao = new UserDao(conn);
            AuthTokenDao tokenDao = new AuthTokenDao(conn);

            //TODO: Generate Family Data
            //User newUser = new User(request.getUsername(), request.getPassword(), request.getEmail(), request.getFirstName(),
                    //request.getLastName(), request.getGender());

            User newUser = new User(request.getUsername(), request.getPassword(), request.getEmail(), request.getFirstName(),
                    request.getLastName(), request.getGender(), request.getPersonID());


            System.out.println("Creating New User");

            userDao.createUser(newUser);

            AuthToken token = new AuthToken(newUser.getUsername());

            System.out.println("Creating new AuthToken");

            tokenDao.createAuthToken(token);

            //Generate 4 Gens of Family

            db.closeConnection(true);

            result = new RegisterResult(token.getAuthToken(), newUser.getUsername(), newUser.getPersonID(), "Success", true);
            return result;

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();

            db.closeConnection(false);

            result = new RegisterResult(e.toString(), false);
            return result;
        }
        /*catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();

            db.closeConnection(false);

            result = new RegisterResult("Failure", false);
            return result;
        }

         */

    }
}

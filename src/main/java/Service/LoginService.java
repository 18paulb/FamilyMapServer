package Service;

import DataAccess.DataAccessException;
import Request.LoginRequest;
import Result.LoginResult;

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
    public static LoginResult loginResponse(LoginRequest request) throws DataAccessException {return new LoginResult(); }

}

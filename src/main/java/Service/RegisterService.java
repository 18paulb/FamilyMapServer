package Service;

import DataAccess.DataAccessException;
import Request.LoginRequest;
import Request.RegisterRequest;
import Result.LoginResult;
import Result.RegisterResult;

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
    public static RegisterResult registerResponse(RegisterRequest request) throws DataAccessException {return new RegisterResult(); }
}

package Service;

import DataAccess.DataAccessException;
import Request.GetAllPersonRequest;
import Result.GetAllPersonResult;

/**
 * Uses Dao classes to find all Events attached to User
 */
public class GetAllPersonService {
    /**
     * Returns a GetAllPerson Result from GetAllPerson Request
     * @param request - The GetAllPersonRequest
     * @return - The GetAllPerson Result Object
     * @throws DataAccessException
     */
    public static GetAllPersonResult personResponse(GetAllPersonRequest request) throws DataAccessException {return new GetAllPersonResult(); }
}

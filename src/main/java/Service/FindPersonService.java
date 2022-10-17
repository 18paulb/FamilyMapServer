package Service;

import DataAccess.DataAccessException;
import Request.FindPersonRequest;
import Result.FindPersonResult;

/**
 * Uses Dao classes to find a Person
 */
public class FindPersonService {
    /**
     * Returns a FindPerson Result from FindPerson Request
     * @param request - The FindPersonRequest
     * @return - The FindPerson Result Object
     * @throws DataAccessException
     */
    public static FindPersonResult personResponse(FindPersonRequest request) throws DataAccessException {return new FindPersonResult(); }
}

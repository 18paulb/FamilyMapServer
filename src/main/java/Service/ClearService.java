package Service;

import DataAccess.DataAccessException;
import Request.ClearRequest;
import Result.ClearResult;

/**
 * Uses Dao classes to clear database
 */
public class ClearService {
    /**
     * Returns a Clear Result from Clear Request
     * @param request - The ClearRequest
     * @return - The Clear Result Object
     * @throws DataAccessException
     */
    public static ClearResult clearResponse(ClearRequest request) throws DataAccessException {return new ClearResult(); }
}

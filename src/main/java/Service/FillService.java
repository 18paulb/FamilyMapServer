package Service;

import DataAccess.DataAccessException;
import Request.FillRequest;
import Request.LoginRequest;
import Result.FillResult;
import Result.LoginResult;

/**
 * Uses Dao classes to interact with database and add info
 */
public class FillService {
    /**
     * Returns a Fill Result from Fill Request
     * @param request - The FillRequest
     * @return - The Fill Result Object
     * @throws DataAccessException
     */
    public static FillResult fillResponse(FillRequest request) throws DataAccessException {return new FillResult(); }
}

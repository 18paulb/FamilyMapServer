package Service;

import DataAccess.DataAccessException;
import Request.LoadRequest;
import Result.LoadResult;

/**
 * Uses Dao to clear database and add new info
 */
public class LoadService {

    /**
     * Returns a Load Result from Login Request
     * @param request - The LoadRequest
     * @return - The Load Result Object
     * @throws DataAccessException
     */
    public static LoadResult loadResponse(LoadRequest request) throws DataAccessException {return new LoadResult(); }
}

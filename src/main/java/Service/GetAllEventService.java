package Service;

import DataAccess.DataAccessException;
import Request.GetAllEventRequest;
import Result.GetAllEventResult;

/**
 * Uses Dao classes to find all Events attached to User
 */
public class GetAllEventService {
    /**
     * Returns a GetAllEvent Result from GetAllEvent Request
     * @param request - The GetAllEventRequest
     * @return - The GetAllEvent Result Object
     * @throws DataAccessException
     */
    public static GetAllEventResult eventResponse(GetAllEventRequest request) throws DataAccessException {return new GetAllEventResult(); }
}

package Service;

import DataAccess.DataAccessException;
import Request.ClearRequest;
import Request.FindEventRequest;
import Result.ClearResult;
import Result.FindEventResult;

/**
 * Uses Dao classes to find an Event
 */
public class FindEventService {
    /**
     * Returns a FindEvent Result from FindEvent Request
     * @param request - The FindEventRequest
     * @return - The FindEvent Result Object
     * @throws DataAccessException
     */
    public static FindEventResult eventResponse(FindEventRequest request) throws DataAccessException {return new FindEventResult(); }
}

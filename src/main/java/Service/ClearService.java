package Service;

import DataAccess.*;
import Request.ClearRequest;
import Result.ClearResult;

import java.sql.Connection;

/**
 * Uses Dao classes to clear database
 */
public class ClearService {
    /**
     * Returns a Clear Result from Clear Request
     * @return - The Clear Result Object
     * @throws DataAccessException
     */
    public static ClearResult clearResponse() throws DataAccessException {
        ClearResult result = new ClearResult();

        Database db = new Database();

        try {
            Connection conn = db.openConnection();

            db.clearTables();

            db.closeConnection(true);

            result = new ClearResult("Clear succeeded", true);

        } catch (Exception e) {
            e.printStackTrace();
            db.closeConnection(false);
            result = new ClearResult("Error: [" + e.toString() + "]", false);
        }
        return result;
    }
}

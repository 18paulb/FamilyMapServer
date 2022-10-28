package DataAccess;

import javax.xml.crypto.Data;
import java.sql.*;

/**
 * Class to handle all database connections
 */
public class Database {

    //Consider making functions static
    private Connection conn;

    /**
     * Opens Connection to SQL Database
     * @return - The created connection
     * @throws DataAccessException
     */
    public Connection openConnection() throws DataAccessException {
        Connection conn = null;
        try {
            // The Structure for this Connection is driver:language:path
            // The path assumes you start in the root of your project unless given a full file path
            final String CONNECTION_URL = "jdbc:sqlite:familymap.sqlite";

            // Open a database connection to the file given in the path
            //Connection conn = DriverManager.getConnection(CONNECTION_URL);
            conn = DriverManager.getConnection(CONNECTION_URL);

            // Start a transaction
            conn.setAutoCommit(false);

            this.conn = conn;

            return conn;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Unable to open connection to database");
        }
    }

    // When we are done manipulating the database it is important to close the connection. This will
    // end the transaction and allow us to either commit our changes to the database (if true is passed in)
    // or rollback any changes that were made before we encountered a potential error (if false is passed in).

    // IMPORTANT: IF YOU FAIL TO CLOSE A CONNECTION AND TRY TO REOPEN THE DATABASE THIS WILL CAUSE THE
    // DATABASE TO LOCK. YOUR CODE MUST ALWAYS CLOSE THE DATABASE NO MATTER WHAT ERRORS
    // OR PROBLEMS ARE ENCOUNTERED

    /**
     * Closes the connection to SQL Databse
     * @param commit - Boolean value for if changes should be committed or not
     */
    public void closeConnection(boolean commit) {
        try {
            if (commit) {
                // This will commit the changes to the database
                conn.commit();
            } else {
                // If we find out something went wrong, pass a false into closeConnection and this
                // will roll back any changes we made during this connection
                conn.rollback();
            }
            conn.close();
            conn = null;
        } catch (SQLException e) {
            // If you get here there are probably issues with your code and/or a connection is being left open
            e.printStackTrace();
        }
    }

    /**
     * Clears all values from tables
     * @throws DataAccessException
     */
    public void clearTables() throws DataAccessException {
        try (Statement stmt = conn.createStatement()) {
            String sql = "DELETE FROM AuthToken;" + "DELETE FROM User;" + "DELETE FROM Events;" + "DELETE FROM Person";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e);
            throw new DataAccessException("Could not clear table");
        }
    }
}

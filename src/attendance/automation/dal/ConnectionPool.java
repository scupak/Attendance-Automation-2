
package attendance.automation.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *The connectionPool stores instances of connection for later reuse. It also takes care of all the fuctionality needed for this purpose. 
 * @author kacpe
 */
public class ConnectionPool extends ObjectPool<Connection>
{
    private static ConnectionPool connectionpool = null;
    private DatabaseConnector dbconnector;
    
   private ConnectionPool() throws AttendanceAutomationDalException, IOException
    {
        super();
        try {
             dbconnector = new DatabaseConnector();
        } catch (IOException ex) {
            throw new AttendanceAutomationDalException("could not get connection credentials", ex);
        }
    }
    
   /**
    * The connectionPoll is a singleton. 
    * @return
    * @throws IOException
    * @throws Exception 
    */
    public static synchronized ConnectionPool getInstance() throws IOException, Exception
    {
        if (connectionpool == null)
        {
            connectionpool = new ConnectionPool();
        }
        return connectionpool;
    }

    /**
     * This method creates a new instance of connection.
     * @return
     * @throws AttendanceAutomationDalException 
     */
    @Override
    protected Connection create() throws AttendanceAutomationDalException {
         try {
             return dbconnector.getConnection();
         } catch (SQLServerException ex) {
             throw new AttendanceAutomationDalException("Could not etablish connection", ex);
         }
    }

    /**
     * This method checks the validity of an instance. 
     * @param o
     * @return
     * @throws AttendanceAutomationDalException 
     */
    @Override
    public boolean validate(Connection o) throws AttendanceAutomationDalException {
       try
        {
            return !o.isClosed();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * This method checks if the instance in question has expired. 
     * @param o
     * @throws AttendanceAutomationDalException 
     */
    @Override
    public void expire(Connection o) throws AttendanceAutomationDalException {
        try
        {
            o.close();
        } catch (SQLException ex)
        {
            throw new AttendanceAutomationDalException("Could not close connection", ex);
        }
    }

   
}

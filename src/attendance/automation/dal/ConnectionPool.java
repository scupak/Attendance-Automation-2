/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author kacpe
 */
public class ConnectionPool extends ObjectPool<Connection>
{

    private static ConnectionPool connectionpool = null;
    private DatabaseConnector dbconnector;

    private ConnectionPool() throws AttendanceAutomationDalException, IOException
    {
        super();
        try
        {
            dbconnector = new DatabaseConnector();
        } catch (IOException ex)
        {
            throw new AttendanceAutomationDalException("could not get connection credentials", ex);
        }
    }

    public static synchronized ConnectionPool getInstance() throws IOException, Exception
    {
        if (connectionpool == null)
        {
            connectionpool = new ConnectionPool();
        }
        return connectionpool;
    }

    @Override
    protected Connection create() throws AttendanceAutomationDalException
    {
        try
        {
            return dbconnector.getConnection();
        } catch (SQLServerException ex)
        {
            throw new AttendanceAutomationDalException("Could not etablish connection", ex);
        }
    }

    @Override
    public boolean validate(Connection o) throws AttendanceAutomationDalException
    {
        try
        {
            return !o.isClosed();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public void expire(Connection o) throws AttendanceAutomationDalException
    {
        try
        {
            o.close();
        } catch (SQLException ex)
        {
            throw new AttendanceAutomationDalException("Could not close connection", ex);
        }
    }

}

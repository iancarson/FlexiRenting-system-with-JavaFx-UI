package Model;

import java.sql.Connection;
import java.sql.SQLException;

public class SelectModel {
    Connection connection;
    public SelectModel()
    {
        try
        {
            this.connection=dbConnection.getConnection();
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        if(this.connection==null)
        {
            System.exit(0);
        }
    }
    public boolean isDatabaseConnected(){return this.connection !=null;}

}

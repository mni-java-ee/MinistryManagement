package mni.ministry.mgmt.db;

import oracle.jdbc.OracleDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection dbConnection;

    static{
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        String user = "muhamad_iqbal";
        String password = "MyNewPassword123";
        try{
            DriverManager.registerDriver(new OracleDriver());
            dbConnection = DriverManager.getConnection(url, user, password);
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    public static synchronized Connection getDbConnection(){
        return dbConnection;
    }
}

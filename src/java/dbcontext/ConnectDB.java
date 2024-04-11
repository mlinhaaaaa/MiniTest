/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbcontext;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author This PC
 */
public class ConnectDB{
    private static ConnectDB instance;
    public ConnectDB() {
    }
    
    public Connection openConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection con = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=PRJ321_93156;", "sa", "Minh1213");
        return con;
    }
    
    public static ConnectDB getInstance() {
        if (instance == null) {
            instance = new ConnectDB();
        }
        return instance;
    }
}

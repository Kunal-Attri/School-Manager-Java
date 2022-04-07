package db;

import java.sql.*;

public class DBConnection {
	
	private static Connection connection;
	
//	private static final String DBCLASS = "com.mysql.cj.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/schoolManagement";
	private static final String DBUSER = "root";
	private static final String DBPASS = "0000";
	private DBConnection() throws ClassNotFoundException,SQLException{
//        Class.forName(DBCLASS);
        connection=DriverManager.getConnection(
        		DBURL, DBUSER, DBPASS);        
    }
	
	public static Connection getDBConnection() throws ClassNotFoundException,SQLException{
        if(connection == null){
            new DBConnection();
        }
        return connection;
    }

}

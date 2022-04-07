package dbController;

import java.sql.*;

import db.DBConnection;
import model.Account;

public class AdminController {
	
	public static Account getAdminAccount() {
		Account admin = null;
		try {
			String SQL="select * from accounts where username = 'root'";
			Connection conn = DBConnection.getDBConnection();
			PreparedStatement stm = conn.prepareStatement(SQL);
			ResultSet rst = stm.executeQuery();
			if (rst.next()) {
				String user = rst.getString("username");
				String pass = rst.getString("password");
				admin = new Account(user, pass);
			}
		} catch (ClassNotFoundException cnfe) {
			System.err.println("JDBC Driver class not found!");
		} catch (SQLException sqle) {
			System.err.println("SQL Error...");
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return admin;
	}
	
	public static int updatePassword(Account admin) {
		try {
			String sql = "update accounts set username= ? ,password= ? where username = '" + 
						  admin.getUsername() + "'";
	        Connection conn = DBConnection.getDBConnection();
	        PreparedStatement stm = conn.prepareStatement(sql);
	        stm.setObject(1, admin.getUsername());
	        stm.setObject(2, admin.getPassword());

	        return  stm.executeUpdate();
		} catch (ClassNotFoundException cnfe) {
			System.err.println("JDBC Driver class not found!");
		} catch (SQLException sqle) {
			System.err.println("SQL Error...");
			sqle.printStackTrace();
		}
		return 0;
	}
	
}

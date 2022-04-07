package dbController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import model.Account;

public class UserController {
	
	public static Account getUserAccount(String username) {
		Account user = null;
		try {
			String SQL="select * from accounts where username = '" + username + "'";
			Connection conn = DBConnection.getDBConnection();
			PreparedStatement stm = conn.prepareStatement(SQL);
			ResultSet rst = stm.executeQuery();
			if (rst.next()) {
				String usrname = rst.getString("username");
				String pass = rst.getString("password");
				user = new Account(usrname, pass);
			}
		} catch (ClassNotFoundException cnfe) {
			System.err.println("JDBC Driver class not found!");
		} catch (SQLException sqle) {
			System.err.println("SQL Error...");
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public static int addUser(Account user) {
		if (!isUserPresent(user.getUsername())) {
			try {
				String SQL="insert into accounts values(?,?)";
				Connection conn = DBConnection.getDBConnection();
				PreparedStatement stm = conn.prepareStatement(SQL);
				stm.setObject(1, user.getUsername());
				stm.setObject(2, user.getPassword());
				
				return stm.executeUpdate();
			} catch (ClassNotFoundException cnfe) {
				System.err.println("JDBC Driver class not found!");
			} catch (SQLException sqle) {
				System.err.println("SQL Error...");
				sqle.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Username is already taken...");
		}
		return 0;
	}
	
	public static int updateUser(Account user) {
		if (isUserPresent(user.getUsername())) {
			try {
				String sql = "update accounts set username= ? , password=? where username= '" + user.getUsername() + "'";
		        Connection conn = DBConnection.getDBConnection();
		        PreparedStatement stm = conn.prepareStatement(sql);
		        stm.setObject(1, user.getUsername());
		        stm.setObject(2, user.getPassword());

		        return  stm.executeUpdate();
		        
			} catch (ClassNotFoundException cnfe) {
				System.err.println("JDBC Driver class not found!");
			} catch (SQLException sqle) {
				System.err.println("SQL Error...");
				sqle.printStackTrace();
			}
		} else {
			System.out.println("Username not found in database...");
		}
		return 0;
	}
	
	public static int deleteUser (String username) {
		if (isUserPresent(username)) {
			try {
				String sql = "delete from accounts where username = '" + username + "'";
		        Connection conn = DBConnection.getDBConnection();
		        PreparedStatement stm = conn.prepareStatement(sql);

		        return  stm.executeUpdate();
		        
			} catch (ClassNotFoundException cnfe) {
				System.err.println("JDBC Driver class not found!");
			} catch (SQLException sqle) {
				System.err.println("SQL Error...");
				sqle.printStackTrace();
			}
		} else {
			System.out.println("User not found in database...");
		}
		return 0;
	}
	
	public static List<Account> searchSpecificUser(String username) {
		try {
			String sql = "select * from accounts where username = ? ";
	        Connection conn = DBConnection.getDBConnection();
	        PreparedStatement stm = conn.prepareStatement(sql);
	        stm.setObject(1, username);
	        ResultSet rst=stm.executeQuery();
	        
	        return UserList(rst);
		} catch (ClassNotFoundException cnfe) {
			System.err.println("JDBC Driver class not found!");
		} catch (SQLException sqle) {
			System.err.println("SQL Error...");
			sqle.printStackTrace();
		}
		return null;
	}
	
	public static List<Account> searchUser(String username) {
		try {
			String sql = "select * from accounts where username rlike ? ";
	        Connection conn = DBConnection.getDBConnection();
	        PreparedStatement stm = conn.prepareStatement(sql);
	        stm.setObject(1, username);
	        ResultSet rst=stm.executeQuery();
	        
	        return UserList(rst);
		} catch (ClassNotFoundException cnfe) {
			System.err.println("JDBC Driver class not found!");
		} catch (SQLException sqle) {
			System.err.println("SQL Error...");
			sqle.printStackTrace();
		}
		return null;
	}
	
	public static List<Account> searchAllUsers() {
		try {
			String sql = "select * from accounts";
	        Connection conn = DBConnection.getDBConnection();
	        PreparedStatement stm = conn.prepareStatement(sql);
	        ResultSet rst=stm.executeQuery();
	        
	        return UserList(rst);	        
		} catch (ClassNotFoundException cnfe) {
			System.err.println("JDBC Driver class not found!");
		} catch (SQLException sqle) {
			System.err.println("SQL Error...");
			sqle.printStackTrace();
		}
		return null;
    }
	
	public static int updatePassword(Account user) {
		try {
			String sql = "update accounts set username= ? ,password= ? where username = '" + user.getUsername() + "'";
	        Connection conn = DBConnection.getDBConnection();
	        PreparedStatement stm = conn.prepareStatement(sql);
	        stm.setObject(1, user.getUsername());
	        stm.setObject(2, user.getPassword());

	        return  stm.executeUpdate();
		} catch (ClassNotFoundException cnfe) {
			System.err.println("JDBC Driver class not found!");
		} catch (SQLException sqle) {
			System.err.println("SQL Error...");
			sqle.printStackTrace();
		}
		return 0;
	}
	
	public static boolean isUserPresent(String username) {
		try {
			String sql = "select * from accounts where username = '" + username + "'";
	        Connection conn = DBConnection.getDBConnection();
	        PreparedStatement stm = conn.prepareStatement(sql);
	        ResultSet rst=stm.executeQuery();
	        
	        if (rst.next()  && rst.getString("username").equals(username)) {
	        	return true; // present
	        }
		} catch (ClassNotFoundException cnfe) {
			System.err.println("JDBC Driver class not found!");
		} catch (SQLException e) {
			System.err.println("SQL Error...");
			e.printStackTrace();
		}
		return false;		
	}
	
	private static List<Account> UserList (ResultSet rst) {
		List<Account> la = new ArrayList<>();
        try {
			while(rst.next()){
			    Account a=new Account(rst.getString("username"),
			    					  rst.getString("password"));
			    la.add(a);
			}
		} catch (SQLException e) {
			System.err.println("SQL Error...");
			e.printStackTrace();
		}
        return la;
	}

}

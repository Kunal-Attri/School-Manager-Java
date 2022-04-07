package dbController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import model.Teacher;

public class TeacherController {
	
	public static int addTeacher (Teacher teacher) {
		try {
			String SQL="insert into teachers values(?,?,?,?,?)";
			Connection conn = DBConnection.getDBConnection();
			PreparedStatement stm = conn.prepareStatement(SQL);
			int id = generateID();
			teacher.setID(id);
			stm.setObject(1, teacher.getID());
			stm.setObject(2, teacher.getName());
			stm.setObject(3, teacher.getAge());
			stm.setObject(4, teacher.getSubject());
			stm.setObject(5, teacher.getMail());
			
			return stm.executeUpdate();
			
		} catch (ClassNotFoundException cnfe) {
			System.err.println("JDBC Driver class not found!");
		} catch (SQLException sqle) {
			System.err.println("SQL Error...");
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static List<Teacher> searchTeacher(int ID) {
		try {
			String sql = "select * from teachers where id = ? ";
	        Connection conn = DBConnection.getDBConnection();
	        PreparedStatement stm = conn.prepareStatement(sql);
	        stm.setObject(1, ID);
	        ResultSet rst=stm.executeQuery();
	        
	        return TeacherList(rst);
		} catch (ClassNotFoundException cnfe) {
			System.err.println("JDBC Driver class not found!");
		} catch (SQLException sqle) {
			System.err.println("SQL Error...");
			sqle.printStackTrace();
		}
		return null;
        
    }
	
	public static List<Teacher> searchTeacher(String name) {
		try {
			String sql = "select * from teachers where name rlike ? ";
	        Connection conn = DBConnection.getDBConnection();
	        PreparedStatement stm = conn.prepareStatement(sql);
	        stm.setObject(1, name);
	        ResultSet rst=stm.executeQuery();
	        
	        return TeacherList(rst);
		} catch (ClassNotFoundException cnfe) {
			System.err.println("JDBC Driver class not found!");
		} catch (SQLException sqle) {
			System.err.println("SQL Error...");
			sqle.printStackTrace();
		}
		return null;
    }
	
	public static List<Teacher> searchAllTeachers(String subject) {
		try {
			String sql = "select * from teachers where subject = ? ";
	        Connection conn = DBConnection.getDBConnection();
	        PreparedStatement stm = conn.prepareStatement(sql);
	        stm.setObject(1, subject);
	        ResultSet rst=stm.executeQuery();
	        
	        return TeacherList(rst);
		} catch (ClassNotFoundException cnfe) {
			System.err.println("JDBC Driver class not found!");
		} catch (SQLException sqle) {
			System.err.println("SQL Error...");
			sqle.printStackTrace();
		}
		return null;
    }
	
	public static List<Teacher> searchAllTeachers() {
		try {
			String sql = "select * from teachers";
	        Connection conn = DBConnection.getDBConnection();
	        PreparedStatement stm = conn.prepareStatement(sql);
	        ResultSet rst=stm.executeQuery();
	        
	        return TeacherList(rst);	        
		} catch (ClassNotFoundException cnfe) {
			System.err.println("JDBC Driver class not found!");
		} catch (SQLException sqle) {
			System.err.println("SQL Error...");
			sqle.printStackTrace();
		}
		return null;
    }	
	
	public static int deleteTeacher(int ID) {
		try {
			String sql = "delete from teachers where id = '" + ID + "'";
	        Connection conn = DBConnection.getDBConnection();
	        PreparedStatement stm = conn.prepareStatement(sql);

	        return  stm.executeUpdate();
	        
		} catch (ClassNotFoundException cnfe) {
			System.err.println("JDBC Driver class not found!");
		} catch (SQLException sqle) {
			System.err.println("SQL Error...");
			sqle.printStackTrace();
		}
		return 0;
    }
	
	public static int updateTeacher(Teacher teacher) {
		try {
			String sql = "update teachers set id= ? ,name= ? ,age= ? ,subject=? ,mail=? where id= '" + teacher.getID() + "'";
	        Connection conn = DBConnection.getDBConnection();
	        PreparedStatement stm = conn.prepareStatement(sql);
	        stm.setObject(1, teacher.getID());
	        stm.setObject(2, teacher.getName());
	        stm.setObject(3, teacher.getAge());
	        stm.setObject(4, teacher.getSubject());
	        stm.setObject(5, teacher.getMail());

	        return  stm.executeUpdate();
	        
		} catch (ClassNotFoundException cnfe) {
			System.err.println("JDBC Driver class not found!");
		} catch (SQLException sqle) {
			System.err.println("SQL Error...");
			sqle.printStackTrace();
		}
		return 0;
    }
	
	public static boolean isIDPresent(int id) {
		try {
			String sql = "select * from teachers where id = ? ";
	        Connection conn = DBConnection.getDBConnection();
	        PreparedStatement stm = conn.prepareStatement(sql);
	        stm.setObject(1, id);
	        ResultSet rst=stm.executeQuery();
	        
	        if (rst.next()  && rst.getInt("id") == id) {
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
	
	public static int generateID() {
		int i = 100;
		while (true) {
			if (!isIDPresent(i)) {
				return i;
			} else {
				i++;
			}
		}
	}
	
	private static List<Teacher> TeacherList (ResultSet rst) {
		List<Teacher> lt = new ArrayList<>();
        try {
			while(rst.next()){
			    Teacher t = new Teacher(rst.getInt("id"), 
			    					  rst.getString("name"),
			    					  rst.getInt("age"),
			    					  rst.getString("subject"),
			    					  rst.getString("mail"));
			    lt.add(t);
			}
		} catch (SQLException e) {
			System.err.println("SQL Error...");
			e.printStackTrace();
		}
        return lt;
	}
}

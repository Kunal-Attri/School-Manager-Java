package dbController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import model.Student;

public class StudentController {
	
	public static int addStudent (Student student) {
		try {
			String SQL="insert into students values(?,?,?,?,?)";
			Connection conn = DBConnection.getDBConnection();
			PreparedStatement stm = conn.prepareStatement(SQL);
			int id = generateID();
			student.setID(id);
			stm.setObject(1, student.getID());
			stm.setObject(2, student.getName());
			stm.setObject(3, student.getAge());
			stm.setObject(4, student.getGrade());
			stm.setObject(5, student.getMail());
			
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
	
	public static List<Student> searchStudent(int ID) {
		try {
			String sql = "select * from students where id = ? ";
	        Connection conn = DBConnection.getDBConnection();
	        PreparedStatement stm = conn.prepareStatement(sql);
	        stm.setObject(1, ID);
	        ResultSet rst=stm.executeQuery();
	        
	        return StudentList(rst);
		} catch (ClassNotFoundException cnfe) {
			System.err.println("JDBC Driver class not found!");
		} catch (SQLException sqle) {
			System.err.println("SQL Error...");
			sqle.printStackTrace();
		}
		return null;
        
    }
	
	public static List<Student> searchStudent(String name) {
		try {
			String sql = "select * from students where name rlike ? ";
	        Connection conn = DBConnection.getDBConnection();
	        PreparedStatement stm = conn.prepareStatement(sql);
	        stm.setObject(1, name);
	        ResultSet rst=stm.executeQuery();
	        
	        return StudentList(rst);
		} catch (ClassNotFoundException cnfe) {
			System.err.println("JDBC Driver class not found!");
		} catch (SQLException sqle) {
			System.err.println("SQL Error...");
			sqle.printStackTrace();
		}
		return null;
    }
	
	public static List<Student> searchAllStudents(int grade) {
		try {
			String sql = "select * from students where class = ? ";
	        Connection conn = DBConnection.getDBConnection();
	        PreparedStatement stm = conn.prepareStatement(sql);
	        stm.setObject(1, grade);
	        ResultSet rst=stm.executeQuery();
	        
	        return StudentList(rst);
		} catch (ClassNotFoundException cnfe) {
			System.err.println("JDBC Driver class not found!");
		} catch (SQLException sqle) {
			System.err.println("SQL Error...");
			sqle.printStackTrace();
		}
		return null;
    }
	
	public static List<Student> searchAllStudents() {
		try {
			String sql = "select * from students";
	        Connection conn = DBConnection.getDBConnection();
	        PreparedStatement stm = conn.prepareStatement(sql);
	        ResultSet rst=stm.executeQuery();
	        
	        return StudentList(rst);	        
		} catch (ClassNotFoundException cnfe) {
			System.err.println("JDBC Driver class not found!");
		} catch (SQLException sqle) {
			System.err.println("SQL Error...");
			sqle.printStackTrace();
		}
		return null;
    }	
	
	public static int deleteStudent(int ID) {
		try {
			String sql = "delete from students where id = '" + ID + "'";
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
	
	public static int updateStudent(Student student) {
		try {
			String sql = "update students set id= ? ,name= ? ,age= ? ,class=? ,mail=? where id= '" + student.getID() + "'";
	        Connection conn = DBConnection.getDBConnection();
	        PreparedStatement stm = conn.prepareStatement(sql);
	        stm.setObject(1, student.getID());
	        stm.setObject(2, student.getName());
	        stm.setObject(3, student.getAge());
	        stm.setObject(4, student.getGrade());
	        stm.setObject(5, student.getMail());

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
			String sql = "select * from students where id = ? ";
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
		int i = 1000;
		while (true) {
			if (!isIDPresent(i)) {
				return i;
			} else {
				i++;
			}
		}
	}
	
	private static List<Student> StudentList (ResultSet rst) {
		List<Student> ls = new ArrayList<>();
        try {
			while(rst.next()){
			    Student s=new Student(rst.getInt("id"), 
			    					  rst.getString("name"),
			    					  rst.getInt("age"),
			    					  rst.getInt("class"),
			    					  rst.getString("mail"));
			    ls.add(s);
			}
		} catch (SQLException e) {
			System.err.println("SQL Error...");
			e.printStackTrace();
		}
        return ls;
	}

}

package manage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dbController.StudentController;
import model.Student;
import utilpkg.Util;

public class StudentManager {
	
	public final static String PORTAL = "Manage Students Portal";
	private static Scanner sc = new Scanner(System.in);
	
	public StudentManager() {
		boolean back = false;
		while (!back) {
			System.out.println("\n" + PORTAL + ":-");
			System.out.println("1. Add new Student\n" +
							   "2. Modify Student Details\n" +
							   "3. Show Student details (by ID)\n" +
							   "4. Show Student details (by Name)\n" +
							   "5. Show all Students (by Class)\n" +
							   "6. Show all Students\n" +
							   "7. Remove Student\n" +
							   "8. Back");
			System.out.print("Action: ");
			
			int inp = sc.nextInt();
			switch (inp) {
			case 1:
				addStudent();
				break;
			case 2:
				modifyStudent();
				break;
			case 3:
				showStudentByID();
				break;
			case 4:
				showStudentByName();
				break;
			case 5:
				showAllStudentsByClass();
				break;
			case 6:
				showAllStudents();
				break;
			case 7:
				deleteStudent();
				break;
			case 8:
				back = true;
				break;
			default:
				System.out.println("Invalid Input...");
				break;
			}
		}
	}
	
	public void addStudent() {
		
		int id = 0;

		System.out.print("Full name: ");
		sc.nextLine();
		String name = sc.nextLine();
		
		System.out.print("Age: ");
		int age = sc.nextInt();
		
		System.out.print("Class: ");
		int grade = sc.nextInt();
		
		System.out.print("Email: ");
		String mail = sc.next();
		
		Student s = new Student(id, name, age, grade, mail);
		
		int success = StudentController.addStudent(s);
		if (success == 1) {
			System.out.println("Details added!");
			System.out.println("New Student ID: " + s.getID());
		} else {
			System.out.println("Error adding details...");
		}
	}
	
	public void modifyStudent() {
		System.out.print("\nStudent ID: ");
		int id = sc.nextInt();
		
		List<Student> ls = StudentController.searchStudent(id);
		if (ls == null || ls.isEmpty()) {
			System.out.println("ID not found in database...");
		} else {
			System.out.println("\nWhat do you want to modify?");
			System.out.println("(1) Name        (2) Age");
			System.out.println("(3) Class       (4) Email");
			System.out.print("Modify: ");
			int inp = sc.nextInt();
			
			switch (inp) {
			case 1:
				System.out.print("New name: ");
				sc.nextLine();
				String name = sc.nextLine();
				ls.get(0).setName(name);
				break;
			case 2:
				System.out.print("New age: ");
				ls.get(0).setAge(sc.nextInt());
				break;
			case 3:
				System.out.print("New class: ");
				ls.get(0).setGrade(sc.nextInt());
				break;
			case 4:
				System.out.print("New email: ");
				ls.get(0).setMail(sc.next());
				break;
			default:
				System.out.println("Invalid Input...");
			}
			int success = StudentController.updateStudent(ls.get(0));
			if (success == 1) {
				System.out.println("Details of student with ID: " + ls.get(0).getID() + " updated!");
				ls = null;
			} else {
				System.out.println("Error updating details...");
			}
		}
	}
	
	public void showStudentByID() {
		System.out.print("\nStudent ID: ");
		int id = sc.nextInt();
		
		List<Student> ls = StudentController.searchStudent(id);
		if (ls == null || ls.isEmpty()) {
			System.out.println("ID not found in database...");
		} else {
			printStudentDetails(ls);
		}
	}
	
	public void showStudentByName() {
		System.out.print("\nStudent Name: ");
		sc.nextLine();
		String name = sc.nextLine();
		
		List<Student> ls = StudentController.searchStudent(name);
		if (ls == null || ls.isEmpty()) {
			System.out.println("Name not found in database...");
		} else {
			printStudentDetails(ls);
		}
	}
	
	public void showAllStudentsByClass() {
		System.out.print("\nClass: ");
		int grade = sc.nextInt();
		
		List<Student> ls = StudentController.searchAllStudents(grade);
		if (ls == null || ls.isEmpty()) {
			System.out.println("No students enrolled in this class...");
		} else {
			printStudentDetails(ls);
		}
	}
	
	public void showAllStudents() {		
		List<Student> ls = StudentController.searchAllStudents();
		if (ls == null || ls.isEmpty()) {
			System.out.println("No students found in database...");
		} else {
			printStudentDetails(ls);
		}
	}
	
	public void deleteStudent() {
		System.out.print("\nStudent ID: ");
		int id = sc.nextInt();
		
		List<Student> ls = StudentController.searchStudent(id);
		if (ls == null || ls.isEmpty()) {
			System.out.println("ID not found in database...");
		} else {
			printStudentDetails(ls);
			System.out.print("Confirm removal(y/n): ");
			String confirm = sc.next();
			if (confirm.equals("y")) {
				int success = StudentController.deleteStudent(ls.get(0).getID());
				if (success == 1) {
					System.out.println("Details of student with ID: " + ls.get(0).getID() + " removed!");
					ls = null;
				} else {
					System.out.println("Error removing details...");
				}
			}			
		}		
	}
	
	public void printStudentDetails (List<Student> ls) {
		List<List<String>> table = new ArrayList<>();
		table.add(Arrays.asList("Student ID",
				                "Name",
				                "Age",
				                "Class",
				                "Email"));
		for (Student s : ls) {
			table.add(Arrays.asList(String.valueOf(s.getID()),
					  s.getName(),
					  String.valueOf(s.getAge()),
					  String.valueOf(s.getGrade()),
					  s.getMail()));
		}
		System.out.println("\n" + Util.formatAsTable(table));
	}
}

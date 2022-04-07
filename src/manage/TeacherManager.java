package manage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dbController.TeacherController;
import model.Teacher;
import utilpkg.Util;

public class TeacherManager {
	
	public final static String PORTAL = "Manage Teachers Portal";
	private static Scanner sc = new Scanner(System.in);
	
	public TeacherManager() {
		boolean back = false;
		while (!back) {
			System.out.println("\n" + PORTAL + ":-");
			System.out.println("1. Add new Teacher\n" +
							   "2. Modify Teacher Details\n" +
							   "3. Show Teacher details (by ID)\n" +
							   "4. Show Teacher details (by Name)\n" +
							   "5. Show all Teachers (by Subject)\n" +
							   "6. Show all Teachers\n" +
							   "7. Remove Teacher\n" +
							   "8. Back");
			System.out.print("Action: ");
			
			int inp = sc.nextInt();
			switch (inp) {
			case 1:
				addTeacher();
				break;
			case 2:
				modifyTeacher();
				break;
			case 3:
				showTeacherByID();
				break;
			case 4:
				showTeacherByName();
				break;
			case 5:
				showAllTeachersBySubject();
				break;
			case 6:
				showAllTeachers();
				break;
			case 7:
				deleteTeacher();
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
	
	public void addTeacher() {
		
		int id = 0;

		System.out.print("Full name: ");
		sc.nextLine();
		String name = sc.nextLine();
		
		System.out.print("Age: ");
		int age = sc.nextInt();
		
		System.out.print("Subject: ");
		String subject = sc.next();
		
		System.out.print("Email: ");
		String mail = sc.next();
		
		Teacher s = new Teacher(id, name, age, subject, mail);
		
		int success = TeacherController.addTeacher(s);
		if (success == 1) {
			System.out.println("Details added!");
			System.out.println("New Teacher ID: " + s.getID());
		} else {
			System.out.println("Error adding details...");
		}
	}
	
	public void modifyTeacher() {
		System.out.print("\nTeacher ID: ");
		int id = sc.nextInt();
		
		List<Teacher> lt = TeacherController.searchTeacher(id);
		if (lt == null || lt.isEmpty()) {
			System.out.println("ID not found in database...");
		} else {
			System.out.println("\nWhat do you want to modify?");
			System.out.println("(1) Name        (2) Age");
			System.out.println("(3) Subject     (4) Email");
			System.out.print("Modify: ");
			int inp = sc.nextInt();
			
			switch (inp) {
			case 1:
				System.out.print("New name: ");
				sc.nextLine();
				String name = sc.nextLine();
				lt.get(0).setName(name);
				break;
			case 2:
				System.out.print("New age: ");
				lt.get(0).setAge(sc.nextInt());
				break;
			case 3:
				System.out.print("New subject: ");
				lt.get(0).setSubject(sc.next());
				break;
			case 4:
				System.out.print("New email: ");
				lt.get(0).setMail(sc.next());
				break;
			default:
				System.out.println("Invalid Input...");
			}
			int success = TeacherController.updateTeacher(lt.get(0));
			if (success == 1) {
				System.out.println("Details of teacher with ID: " + lt.get(0).getID() + " updated!");
				lt = null;
			} else {
				System.out.println("Error updating details...");
			}
		}
	}
	
	public void showTeacherByID() {
		System.out.print("\nTeacher ID: ");
		int id = sc.nextInt();
		
		List<Teacher> lt = TeacherController.searchTeacher(id);
		if (lt == null || lt.isEmpty()) {
			System.out.println("ID not found in database...");
		} else {
			printTeacherDetails(lt);
		}
	}
	
	public void showTeacherByName() {
		System.out.print("\nTeacher Name: ");
		sc.nextLine();
		String name = sc.nextLine();
		
		List<Teacher> lt = TeacherController.searchTeacher(name);
		if (lt == null || lt.isEmpty()) {
			System.out.println("Name not found in database...");
		} else {
			printTeacherDetails(lt);
		}
	}
	
	public void showAllTeachersBySubject() {
		System.out.print("\nSubject: ");
		String subject = sc.next();
		
		List<Teacher> lt = TeacherController.searchAllTeachers(subject);
		if (lt == null || lt.isEmpty()) {
			System.out.println("No teachers enrolled in this subject...");
		} else {
			printTeacherDetails(lt);
		}
	}
	
	public void showAllTeachers() {		
		List<Teacher> lt = TeacherController.searchAllTeachers();
		if (lt == null || lt.isEmpty()) {
			System.out.println("No teachers found in database...");
		} else {
			printTeacherDetails(lt);
		}
	}
	
	public void deleteTeacher() {
		System.out.print("\nTeacher ID: ");
		int id = sc.nextInt();
		
		List<Teacher> lt = TeacherController.searchTeacher(id);
		if (lt == null || lt.isEmpty()) {
			System.out.println("ID not found in database...");
		} else {
			printTeacherDetails(lt);
			System.out.print("Confirm deletion(y/n): ");
			String confirm = sc.next();
			if (confirm.equals("y")) {
				int success = TeacherController.deleteTeacher(lt.get(0).getID());
				if (success == 1) {
					System.out.println("Details of teacher with ID: " + lt.get(0).getID() + " deleted!");
					lt = null;
				} else {
					System.out.println("Error deleting details...");
				}
			}			
		}		
	}
	
	public void printTeacherDetails (List<Teacher> lt) {
		List<List<String>> table = new ArrayList<>();
		table.add(Arrays.asList("Teacher ID",
				                "Name",
				                "Age",
				                "Subject",
				                "Email"));
		for (Teacher t : lt) {
			table.add(Arrays.asList(String.valueOf(t.getID()),
					  t.getName(),
					  String.valueOf(t.getAge()),
					  String.valueOf(t.getSubject()),
					  t.getMail()));
		}
		System.out.println("\n" + Util.formatAsTable(table));
	}

}

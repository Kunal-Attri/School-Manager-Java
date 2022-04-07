package manage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dbController.UserController;
import model.Account;
import utilpkg.Util;

public class UserManager {
	
	public final static String PORTAL = "Manage Users Portal";
	private static Scanner sc = new Scanner(System.in);
	
	public UserManager() {
		boolean back = false;
		while (!back) {
			System.out.println("\n" + PORTAL + ":-");
			System.out.println("1. Add new User\n" +
							   "2. Reset User Password\n" +
							   "3. Delete User\n" +
							   "4. Show User details (by Name)\n" +
							   "5. Show all Users\n" +
							   "6. Back");
			System.out.print("Action: ");
			
			int inp = sc.nextInt();
			switch (inp) {
			case 1:
				addUser();
				break;
			case 2:
				resetUserPassword();
				break;
			case 3:
				deleteUser();
				break;
			case 4:
				showUserByName();
				break;
			case 5:
				showAllUsers();
				break;
			case 6:
				back = true;
				break;
			default:
				System.out.println("Invalid Input...");
				break;
			}
		}
	}
	
	private void addUser() {
		System.out.print("\nUsername: ");
		String username = sc.next();
		String password = "password";
		Account user = new Account(username, Util.getMd5(password));
		int success = UserController.addUser(user);
		if (success == 1) {
			System.out.println("User " + username + " added successfully!");
			System.out.println("Default Password: " + password);
		} else {
			System.out.println("Error adding user...");
		}
	}
	
	private void resetUserPassword() {
		System.out.print("Username: ");
		String username = sc.next();
		String password = "password";
		Account user = new Account(username, Util.getMd5(password));
		if (!username.equals("root")) {
			int success = UserController.updateUser(user);
			if (success == 1) {
				System.out.println("User " + username + "'s password reset successfull!");
				System.out.println("Default Password: " + password);
			} else {
				System.out.println("Error resetting user password...");
			}
		} else {
			System.out.println("You can'r change or reset admin password from here...");
		}
	}
	
	private void deleteUser() {
		System.out.print("Username: ");
		String username = sc.next();
		
		List<Account> la = UserController.searchSpecificUser(username);
		if (la == null || la.isEmpty()) {
			System.out.println("User not found in database...");
		} else {
			printUserDetails(la);
			System.out.print("Confirm removal(y/n): ");
			String confirm = sc.next();
			if (confirm.equals("y")) {
				int success = UserController.deleteUser(la.get(0).getUsername());
				if (success == 1) {
					System.out.println("Details of User " + la.get(0).getUsername() + " removed!");
					la = null;
				} else {
					System.out.println("Error removing details...");
				}
			}
		}
	}
	
	private void showUserByName() {
		System.out.print("Username: ");
		String username = sc.next();
		
		List<Account> la = UserController.searchUser(username);
		if (la == null || la.isEmpty()) {
			System.out.println("User not found in database...");
		} else {
			printUserDetails(la);
		}
	}
	
	private void showAllUsers() {
		List<Account> la = UserController.searchAllUsers();
		if (la == null || la.isEmpty()) {
			System.out.println("User not found in database...");
		} else {
			printUserDetails(la);
		}
	}
	
	public void printUserDetails (List<Account> la) {
		List<List<String>> table = new ArrayList<>();
		table.add(Arrays.asList("Sr. no.", "Username"));
		int i = 1;
		for (Account a : la) {
			table.add(Arrays.asList(String.valueOf(i), a.getUsername()));
			i++;
		}
		System.out.println("\n" + Util.formatAsTable(table));
	}

}

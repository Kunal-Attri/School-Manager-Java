package manage;

import java.util.Scanner;

import dbController.UserController;
import model.Account;
import utilpkg.Util;

public class User {
	
	Account user;
	public final static String PORTAL = "User Portal";
	private static Scanner sc = new Scanner(System.in);
	
	public User(Account acc) {
		user = UserController.getUserAccount(acc.getUsername());
		
		boolean back = false;
		while (!back) {
			System.out.println("\n" + PORTAL + ":-\n" +
					   "1. Manage Students\n" +
					   "2. Manage Teachers\n" +
					   "3. Change Password\n" +
					   "4. Logout & exit");
			System.out.print("Action: ");
			int inp = sc.nextInt();
			
			switch (inp) {
			case 1:
				new StudentManager();
				break;
			case 2:
				new TeacherManager();
				break;
			case 3:
				changePassword();
				break;
			case 4:
				back = true;
				System.out.println("Bye!");
				break;
			default:
				System.out.println("Invalid Input...");
				break;
			}
		}	
	}	
	
	private void changePassword() {
		System.out.print("Enter old Password: ");
		sc.nextLine();
		String oldPassword = Util.getMd5(sc.nextLine());
		
		if (oldPassword.equals(user.getPassword())) {
			
			System.out.print("Enter new Password: ");
			user.setPassword(Util.getMd5(sc.nextLine()));
			System.out.print("Retype new Password: ");
			String newPassword = Util.getMd5(sc.nextLine());
			
			if (user.getPassword().equals(newPassword)) {
				
				int success = UserController.updatePassword(user);
				if (success == 1) {
					System.out.println("User password changed successfully!");
				} else {
					System.out.println("Error updating password...");
				}
			} else {
				System.out.println("Mismatch in the entered password...");
				user.setPassword(oldPassword);
			}
		} else {
			System.out.println("Wrong password entered...");
		}
	}
}

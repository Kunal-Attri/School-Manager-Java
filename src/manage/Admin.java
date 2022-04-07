package manage;

import java.util.Scanner;

import dbController.AdminController;
import model.Account;
import utilpkg.Util;

public class Admin {
	
	Account admin;
	public final static String PORTAL = "Admin Portal";
	public static Scanner sc = new Scanner(System.in);
	
	public Admin()  {
		admin = AdminController.getAdminAccount();
		
		boolean back = false;
		while (!back) {
			System.out.println("\n" + PORTAL + ":-\n" +
							   "1. Manage Students\n" +
							   "2. Manage Teachers\n" +
							   "3. Manage Users\n" +
							   "4. Change Root Password\n" +
							   "5. Logout & Exit");
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
				new UserManager();
				break;
			case 4:
				changePassword();
				break;
			case 5:
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
		
		if (oldPassword.equals(admin.getPassword())) {
			
			System.out.print("Enter new Password: ");
			admin.setPassword(Util.getMd5(sc.nextLine()));
			System.out.print("Retype new Password: ");
			String newPassword = Util.getMd5(sc.nextLine());
			
			if (admin.getPassword().equals(newPassword)) {
				
				int success = AdminController.updatePassword(admin);
				if (success == 1) {
					System.out.println("Admin password changed successfully!");
				} else {
					System.out.println("Error updating password...");
				}
			} else {
				System.out.println("Mismatch in the entered password...");
				admin.setPassword(oldPassword);
			}
		} else {
			System.out.println("Wrong password entered...");
		}
	}
}

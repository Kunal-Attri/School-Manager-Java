package main;

import java.util.Scanner;

import dbController.LoginController;
import manage.Admin;
import manage.User;
import model.Account;
import utilpkg.Util;

public class Login {
	
	public static Scanner sc = new Scanner(System.in);
	
	public static void login() {
		int tries = 3;
		
		while (tries > 0) {
			System.out.println("\nEnter Credentials to Login:-");
			
			System.out.print("Username: ");
			String username = sc.next();
			
			System.out.print("Password: ");
			sc.nextLine();
			String password = Util.getMd5(sc.nextLine());
			
			Account login = new Account(username, password);
			
			if (LoginController.verifyCredentials(login)) {
				if (login.getUsername().equals("root")) {
					new Admin();
				} else {
					new User(login);
				}
				tries = 0;
			} else {
				System.out.println("Username or Password is incorrect...");
				tries--;
			}
		}
	}
}

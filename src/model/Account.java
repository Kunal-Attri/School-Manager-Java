package model;

public class Account {
	private String username;
	private String password;
	
	public Account(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		if (username != "root") {
			this.username = username;
		} else {
			System.err.println("Root username can't be changed...");
		}	
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}

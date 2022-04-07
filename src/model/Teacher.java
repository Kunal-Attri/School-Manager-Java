package model;

public class Teacher {
	
	private int ID;
	private String name;
	private int age;
	private String subject;
	private String mail;
	
	public Teacher(int iD, String name, int age, String subject, String mail) {
		super();
		ID = iD;
		this.name = name;
		this.age = age;
		this.subject = subject;
		this.mail = mail;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public void display() {
		System.out.println("\nTeacher ID: " + ID);
		System.out.println("Name: " + name);
		System.out.println("Age: " + age);
		System.out.println("Subject: " + subject);
		System.out.println("Email: " + mail);
	}

}

package model;

public class Student {
	
	private int ID = 0;
	private String name;
	private int age;
	private int grade;
	private String mail;
	
	public Student(int iD, String Name, int age, int grade, String mail) {
		super();
		ID = iD;
		this.name = Name;
		this.age = age;
		this.grade = grade;
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

	public void setName(String Name) {
		this.name = Name;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public void display() {
		System.out.println("\nStudent ID: " + ID);
		System.out.println("Name: " + name);
		System.out.println("Age: " + age);
		System.out.println("Class: " + grade);
		System.out.println("Email: " + mail);
		
	}

}

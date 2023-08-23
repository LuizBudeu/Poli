package ex2;

public class Student{
	private String name;
	private int rollNo;
	
	Student(String name,int rollNo){
		this.name = name;
		this.rollNo = rollNo;
	}
	
	public String getName(){
		return name;
	}

	public int getRollNo() {
		return rollNo;
	}

	public void setName(String name2) {
		this.name = name2;
	}
}

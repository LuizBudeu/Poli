package ex2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.AbstractDocument.Content;



public class StudentDaoImpl implements StudentDao {

	java.io.File f1 = new java.io.File("studentdatabase.txt");
	java.io.PrintWriter out1;

	public StudentDaoImpl() {
		
		try {
			out1 = new java.io.PrintWriter(f1);
			out1.print("Robert, 0\nJohn, 1\n");
			out1.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteStudent(Student student) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("studentdatabase.txt"));
			String line = null;
			
			do {
				line = br.readLine();
				if (line.contains(String.valueOf(student.getRollNo())))
					removeLineFromFile("studentdatabase.txt", line);
			} while (line != null);
			
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Student: RollNo" + student.getRollNo() + ",deleted from database");
	}


	@Override
	public List<Student> getAllStudents() {
		List<Student> students = null;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("studentdatabase.txt"));
			String line = null;
			
			do {
				line = br.readLine();
				
				String name = "";
				int i;
				for (i = 0; i < line.length(); i++) {
					if (line.charAt(i) != ',') {
						name += line.charAt(i);
					}
				}
				
				int number;
				String nString = "";
				for (int j = i+1; line.charAt(j) != '\n'; j++) {
						nString += line.charAt(j);
					}
				number = Integer.parseInt(nString);
				
				Student student = new Student(name, number);
				students.add(student);
			} while (line != null);
			
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return students;
	}

	@Override
	public Student getStudent(int rollNo) {
		Student student = null;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("studentdatabase.txt"));
			String line = null;
			
			do {
				line = br.readLine();
				if (line.contains(String.valueOf(rollNo))) {
					String name = "";
					int i;
					for (i = 0; i < line.length(); i++) {
						if (line.charAt(i) != ',') {
							name += line.charAt(i);
						}
					}
					
					int number;
					String nString = "";
					for (int j = i+1; line.charAt(j) != '\n'; j++) {
							nString += line.charAt(j);
						}
					number = Integer.parseInt(nString);
					
					student = new Student(name, number);
				}
					
			} while (line != null);
			
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return student;
	}

	@Override
	public void updateStudent(Student student) {
		String content = "";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("studentdatabase.txt"));
			String line = null;
			
			do {
				line = br.readLine();
				content += line;
			} while (line != null);
			
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String newName = student.getName() +", "+ String.valueOf(student.getRollNo());
			
		
		
		
		System.out.println("Student: RollNo" + student.getRollNo() + ",updated in the database");
	}
	
	public void removeLineFromFile(String file, String lineToRemove) {

	    try {

	      File inFile = new File(file);

	      if (!inFile.isFile()) {
	        System.out.println("Parameter is not an existing file");
	        return;
	      }

	      //Construct the new file that will later be renamed to the original filename.
	      File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

	      BufferedReader br = new BufferedReader(new FileReader(file));
	      PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

	      String line = null;

	      //Read from the original file and write to the new
	      //unless content matches data to be removed.
	      while ((line = br.readLine()) != null) {

	        if (!line.trim().equals(lineToRemove)) {

	          pw.println(line);
	          pw.flush();
	        }
	      }
	      pw.close();
	      br.close();

	      //Delete the original file
	      if (!inFile.delete()) {
	        System.out.println("Could not delete file");
	        return;
	      }

	      //Rename the new file to the filename the original file had.
	      if (!tempFile.renameTo(inFile))
	        System.out.println("Could not rename file");

	    }
	    catch (FileNotFoundException ex) {
	      ex.printStackTrace();
	    }
	    catch (IOException ex) {
	      ex.printStackTrace();
	    }
	  }
}

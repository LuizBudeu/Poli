package ex3;

import java.io.FileNotFoundException;
import java.io.IOException;

public class DoisJeitos {
	public static void main(String[] args) {

		java.io.File f1 = new java.io.File("s1.txt");
		java.io.PrintWriter out1;
		try {
			out1 = new java.io.PrintWriter(f1);
			out1.print(5);
			out1.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		

		
		java.io.File f2 = new java.io.File("s2.txt");
		java.io.FileOutputStream out2;
		try {
			out2 = new java.io.FileOutputStream(f2);
			out2.write(5);
			out2.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}
}

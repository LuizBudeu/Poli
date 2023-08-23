package ex2;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

public class TestaStudent {
	
	@Test 
	public void testaConstructor() {
		StudentDaoImpl s = new StudentDaoImpl();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("studentdatabase.txt"));
			String line = null;
			
			line = br.readLine();
			assertEquals(line, "Robert, 0");
			line = br.readLine();
			assertEquals(line, "John, 1");
			
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

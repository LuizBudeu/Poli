package ex3;

import java.io.*;


/* Ocorre essa diferença pois 'OutputStream' é usado
 * para arquivos binários, enquanto 'PrintWriter' é 
 * usado para a impressão de texto. Isso quer dizer 
 * que este usa encodificação correta enquanto aquele
 * não.
 */




public class TestaDiferenca {
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("s1.txt"));
			String line = null;
			
			do {
				line = br.readLine();
				if (line != null)
					System.out.println(line);
			} while (line != null);
			
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("s2.txt"));
			String line = null;
			
			do {
				line = br.readLine();
				if (line != null)
					System.out.println(line);
			} while (line != null);
			
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

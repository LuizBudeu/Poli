package ex1;


import java.io.PrintWriter;




public class TestaDesempenho {
	public static void main(String[] args) {
		
		long tStart = System.currentTimeMillis();

	    for(int i = 0; i < 1000; i ++){
	    	try {
				PrintWriter writer = new PrintWriter("hello.txt", "UTF-8");
				writer.println("a");
				writer.close();
			}
			catch(Exception e) {
					System.out.println(e);
			}
	    }

	    long tEnd = System.currentTimeMillis();
	    long tResult = tEnd - tStart;

	    System.out.println("Tempo de Execução do PrintWriter = "+tResult+" ms");
	    
	    
	    
	    tStart = System.currentTimeMillis();

	    for(int i = 0; i < 1000; i ++){
	    	System.out.println("a");
	    }

	    tEnd = System.currentTimeMillis();
	    tResult = tEnd - tStart;

	    System.out.println("Tempo de Execução do System.out.println = "+tResult+" ms");
	}
}

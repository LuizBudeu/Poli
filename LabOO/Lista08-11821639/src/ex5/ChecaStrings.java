package ex5;

/* Ocorre esta diferença porque 'String' é imutável, enquanto
 * 'StringBuffer' é mutável. Isto quer dizer que quando usamos
 * o operador '+' com 'String', o JVM cria inteiramente um novo 
 * objeto, ou seja, é necessário criar novos espaços na memória
 * toda vez que usamos o '+', tornando o processo mais lento. 
 * Como 'StringBuffer' é mutável, não é necessário criar esses
 * novos espaços na memória sempre, fazendo com que o processo
 * seja bem mais rápido.
 */


public class ChecaStrings {
	public static void main(String[] args) {
		String strFinal = "";
	    long tStart = System.currentTimeMillis();

	    for(int i = 0; i < 100000; i ++){
	           strFinal += "a";
	    }

	    long tEnd = System.currentTimeMillis();
	    long tResult = tEnd - tStart;

	    System.out.println("Tempo de Execução com operador'+' = "+tResult+" ms");


	    StringBuffer strBuffer = new StringBuffer();
	    tStart = System.currentTimeMillis();
	    for(int i = 0; i < 100000; i ++){
	           strBuffer.append("a");
	    }
	    tEnd = System.currentTimeMillis();
	    tResult = tEnd - tStart;
	    System.out.println("Tempo de Execução com StringBuffer = "+tResult+" ms");
	}
	
}

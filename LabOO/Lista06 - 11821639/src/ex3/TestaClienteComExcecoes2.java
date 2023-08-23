package ex3;


import org.junit.Before;
import org.junit.Test;



public class TestaClienteComExcecoes2 {
	
	ClienteComExcecoes2 c1;
	ClienteComExcecoes2 c2;
		
	@Before
	public void setUp() {
		c1 = new ClienteComExcecoes2("Luiz");
		c2 = new ClienteComExcecoes2("Pedro");
	}

	@Test 
	public void TDeposito() throws NegativeException2 {
		
		/* Neste caso, essa função necessita do 'throws declaration' porque,
		 * ao contrário do 'NegativeException' que é derivada de RuntimeException,
		 * a 'NegativeException2' é derivada de Exception. No Java, classes derivadas
		 * de 'Error' ou 'RuntimeException' não precisam dessa declaração, no entanto
		 * 'Exception' não é uma subclasse de 'Error' nem de 'RuntimeException', e 
		 * portanto necessita da declaração, que nem o resto das cláusulas de 
		 * 'Throwables', com exceção daquelas duas.
		 */
		
		try {
			c1.deposito(-10);
		} catch (NegativeException2 e) {
			System.out.println("Peguei exceção do depósito");
		}
		finally {
			c1.imprime();
			System.out.println();
		}
	}
	
	@Test 
	public void TSaque() {
		c2.setSaldo(100);
		
		try {
			c2.saque(-10);
		} catch (NegativeException2 e) {
			System.out.println("Peguei exceção do saque");
		}
		finally {
			c2.imprime();
			System.out.println();
		}
		
		try {
			c2.saque(200);
		} catch (NegativeException2 e) {
			System.out.println("Peguei exceção do saque");
		}
		finally {
			c2.imprime();
			System.out.println();
		}
	}
}
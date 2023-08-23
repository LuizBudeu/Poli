package ex2;


import org.junit.Before;
import org.junit.Test;



public class TestaClienteComExcecoes {
	
	ClienteComExcecoes c1;
	ClienteComExcecoes c2;
		
	@Before
	public void setUp() {
		c1 = new ClienteComExcecoes("Luiz");
		c2 = new ClienteComExcecoes("Pedro");
	}

	@Test 
	public void TDeposito() {
		c1.deposito(-10);
		try {
			
		} catch (NegativeException e) {
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
		} catch (NegativeException e) {
			System.out.println("Peguei exceção do saque");
		}
		finally {
			c2.imprime();
			System.out.println();
		}
		
		try {
			c2.saque(200);
		} catch (NegativeException e) {
			System.out.println("Peguei exceção do saque");
		}
		finally {
			c2.imprime();
			System.out.println();
		}
	}
}

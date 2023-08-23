import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TesteCliente{
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	@BeforeEach
	public void setUpStreams() {
	 System.setOut(new PrintStream(outContent));
	 System.setErr(new PrintStream(errContent));
	}
	@AfterEach
	public void cleanUpStreams() {
	 System.setOut(null);
	 System.setErr(null);
	}
	
	@Test 
	public void testaContas() {
		Cliente c1 = new Cliente("Luiz");
		Cliente c2 = new Cliente("Rafael", 100);
		
		assertEquals("Luiz", c1.getNome());
		assertEquals("Rafael", c2.getNome());
		
		assertEquals(0, c1.getSaldo());
		assertEquals(100, c2.getSaldo());
		
		
		assertEquals(1001, c1.getNConta());
		assertEquals(1002, c2.getNConta());
	}
	
	@Test 
	public void testaImpressao() {
		Cliente c3 = new Cliente("Fernando");
		
		c3.imprime();
		assertEquals("Nome, número da conta, saldo: Fernando, 1005, 0.0", outContent.toString());
	}
	
	@Test 
	public void testaSaldoMaiorIgual() {
		Cliente c4 = new Cliente("Carla", 100);
		
		assertEquals(true, c4.saldoMaiorIgual(30));
		assertEquals(true, c4.saldoMaiorIgual(100));
		assertEquals(false, c4.saldoMaiorIgual(300));
	}
	
	@Test 
	public void testaSaque() {
		Cliente c5 = new Cliente("Julia", 100);
		
		c5.saque(50);
		assertEquals(50, c5.getSaldo());
	}
	
	@Test 
	public void testaDeposito() {
		Cliente c6 = new Cliente("Artur");
		
		c6.deposito(100);
		assertEquals(100, c6.getSaldo());
	}
}



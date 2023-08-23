import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestaCliente2 {

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
	public void testaBloqueado() {
		Cliente2 c1 = new Cliente2("Julia", -600);
		
		assertEquals(true, c1.getBloqueado());
	}
	
	
	@Test 
	public void testaQuantosBloqueados() {
		Cliente2 c2 = new Cliente2("Luiz", -100);
		Cliente2 c3 = new Cliente2("Fernando", 5);
		Cliente2 c4 = new Cliente2("Rafael", -200);
		System.out.print(Cliente2.quantosBloqueados);
		
		// Como conta também os Clientes2 já criados, Cliente2.quantosBloqueados == 3. Clientes bloqueados: Julia, Luiz, Fernando
		assertEquals(3, Cliente2.quantosBloqueados);
	}
	
	@Test 
	public void testaSaque() {
		Cliente2 c5 = new Cliente2("Maria", -100);
		
		int s = c5.saque(80);
		assertEquals(0, s);     //'s' == 0 quer dizer que o saque foi interrompido porque o cliente está bloqueado
	} 
	
}

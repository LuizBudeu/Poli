
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class TestaCliente3 {
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
	public void testaClientes() throws Throwable {
		Cliente3 c1 = Cliente3.criaCliente("Luiz1");
		Cliente3 c2 = Cliente3.criaCliente("Luiz2");
		Cliente3 c3 = Cliente3.criaCliente("Luiz3");
		Cliente3 c4 = Cliente3.criaCliente("Luiz4");
		Cliente3 c5 = Cliente3.criaCliente("Luiz5");
		Cliente3 c6 = Cliente3.criaCliente("Luiz6");
		
		assertNotNull(c1);
		assertNotNull(c2);
		assertNotNull(c3);
		assertNotNull(c4);
		assertNotNull(c5);
		assertNull(c6);
		
		c1.fimCliente();

		Cliente3 c7 = Cliente3.criaCliente("Aa");
		assertNotNull(c7);

	}
	
	
}

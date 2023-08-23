package br.ime.usp.mac321.ep1.ex1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


class TestaExercicio1 {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private static PrintStream out = System.out;
	private static PrintStream err = System.err;
	
	//private Controlador c;
	
	//@BeforeAll
	//public void setUp() {
	//	c = new Controlador();
	//}
	
	@BeforeEach
	public void setUpStreams() {
	   System.setOut(new PrintStream(outContent));
	   System.setErr(new PrintStream(errContent));
	}
	@AfterEach
	public void cleanUpStreams() {
		String output = outContent.toString();
	   
	   System.setOut(out);
	   System.setErr(err);
	   System.out.print(output);

	}	
	
	@Test
	public void tDesc() {
	   Evento e = new EventoSimples(0);
	   assertEquals("Nada", e.descricao());

	}

	@Test
	public void tAção() {
	   Evento e = new EventoSimples(0);
	   e.acao();
	   assertEquals("Ação!", outContent.toString());
	}
	@Test
	public void tCont() {
		Evento e = new EventoSimples(0);
		Controlador c = new Controlador();
		c.insere(e);
		c.run();
		assertEquals("Ação!Nada", outContent.toString());
	}

}

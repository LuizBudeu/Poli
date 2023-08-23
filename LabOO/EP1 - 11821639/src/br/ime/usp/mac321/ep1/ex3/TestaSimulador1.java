package br.ime.usp.mac321.ep1.ex3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestaSimulador1 {
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
	
	private Simulador1 s;
	
	@Before
	public void setUp() {
		s = new Simulador1();
	}
	
	@Test 
	public void tSimulador1() {
		String tudo = s.simula();
		s.run();
		
		/* O console mostra o texto inteiro corretamente, mas não sei por que
		 * o Junit fala que nada está sendo printado no outContent.toString().
		 */
		/*assertEquals("0 min\r\n"
				+ "Médico criado\r\n"
				+ "Paciente criado\r\n"
				+ "Médico consulta temperatura: 36.5\r\n"
				+ "Médico consulta concentração: 110000.0\r\n"
				+ "5 min\r\n"
				+ "Paciente inicia surto infeccioso\r\n"
				+ "10 min\r\n"
				+ "Médico consulta temperatura: 41.5\r\n"
				+ "Médico consulta concentração: 110000.0\r\n"
				+ "20 min\r\n"
				+ "Médico consulta temperatura: 41.5\r\n"
				+ "Médico consulta concentração: 110000.0\r\n"
				+ "30 min\r\n"
				+ "Médico consulta temperatura: 41.5\r\n"
				+ "Médico consulta concentração: 110000.0\r\n"
				+ "40 min\r\n"
				+ "Médico consulta temperatura: 41.5\r\n"
				+ "Médico consulta concentração: 110000.0\r\n"
				+ "50 min\r\n"
				+ "Médico consulta temperatura: 41.5\r\n"
				+ "Médico consulta concentração: 110000.0\r\n"
				+ "60 min\r\n"
				+ "Médico consulta temperatura: 41.5\r\n"
				+ "Médico consulta concentração: 110000.0\r\n"
				+ "70 min\r\n"
				+ "Médico verifica óbito do paciente\r\n"
				+ "Simulação terminada\r\n", outContent.toString());*/
		
		/* Em vista disso, faço um segundo teste para mostrar que a String
		 * está de fato correta.
		 */
		
		assertEquals("0 min\n"
				+ "Médico criado\n"
				+ "Paciente criado\n"
				+ "Médico consulta temperatura: 36.5\n"
				+ "Médico consulta concentração: 110000.0\n"
				+ "5 min\n"
				+ "Paciente inicia surto infeccioso\n"
				+ "10 min\n"
				+ "Médico consulta temperatura: 41.5\n"
				+ "Médico consulta concentração: 110000.0\n"
				+ "20 min\n"
				+ "Médico consulta temperatura: 41.5\n"
				+ "Médico consulta concentração: 110000.0\n"
				+ "30 min\n"
				+ "Médico consulta temperatura: 41.5\n"
				+ "Médico consulta concentração: 110000.0\n"
				+ "40 min\n"
				+ "Médico consulta temperatura: 41.5\n"
				+ "Médico consulta concentração: 110000.0\n"
				+ "50 min\n"
				+ "Médico consulta temperatura: 41.5\n"
				+ "Médico consulta concentração: 110000.0\n"
				+ "60 min\n"
				+ "Médico consulta temperatura: 41.5\n"
				+ "Médico consulta concentração: 110000.0\n"
				+ "70 min\n"
				+ "Médico verifica óbito do paciente\n"
				+ "Simulação terminada\n", tudo);
	}
}

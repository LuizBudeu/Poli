package br.ime.usp.mac321.ep1.ex2;

import org.junit.*;

import static org.junit.Assert.*;

public class TestaPaciente {
	
	Paciente p;

	@Before
	public void setUp() {
		p = new Paciente(36.5, 120000, 5.7, 40, 1000);
	}
	
	@Test 
	public void tPaciente() {
		assertEquals(p.getTemperaturaBasal(), 36.5, 0.00001);
		assertEquals(p.getConcentracaoPAC(), 120000, 0.00001);
		assertEquals(p.getAumentoTemperatura(), 5.7, 0.00001);
		assertEquals(p.getFrequenciaSurtos(), 40);
		assertEquals(p.getVelocidadeAumentoPAC(), 1000, 0.00001);
		
	}
	
	@Test 
	public void tTemp() {
		assertEquals(p.getTemp(1), 42.2, 0.000001);
	}
	
	@Test 
	public void tPAC() {
		assertEquals(p.getPAC(1), 120000, 0.00001);
	}
	
	@Test 
	public void tMorto() {
		p.mata();
		assertFalse(p.taVivo());
		assertEquals(p.getTemp(1), -42.2, 0.000001);
		assertEquals(p.getPAC(1), -120000, 0.00001);
	
	}
}

package br.ime.usp.mac321.ep1.ex2;

import org.junit.*;

import static org.junit.Assert.*;

public class TestaMedico {

	Medico m;
	Droga[] c = {new Droga(1, 2), new Droga(4, 5)};
	
	@Before
	public void setUp() {
		m = new Medico(10, c, 5);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void tMedico() {
		assertEquals(m.getFrequencia(), 10);
		assertEquals(m.getDuracao(), 5);
		assertEquals(m.getConjunto(), c);
	}
}

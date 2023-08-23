package br.ime.usp.mac321.ep1.ex2;

import org.junit.*;

import static org.junit.Assert.*;

public class TestaDroga {

	Droga d;
	
	@Before
	public void setUp() {
		d = new Droga(1, 2);
	}
	
	@Test 
	public void tDroga() {
		assertEquals(d.getVt(), 1, 0.000001);
		assertEquals(d.getVc(), 2, 0.000001);
	}
	
}

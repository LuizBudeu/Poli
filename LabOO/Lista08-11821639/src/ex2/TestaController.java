package ex2;

import static org.junit.Assert.*;

import org.junit.Test;

import ex1.Controller;


public class TestaController {
	
	@Test
	public void testaNormal() {
		assertEquals(Controller.calculaFatorial(5), 120);
		assertEquals(Controller.calculaFatorial(0), 1);
	}
	
	@Test
	public void testaNegativo() {
		try {
			assertEquals(Controller.calculaFatorial(-10), 12);
		} catch (RuntimeException e) {
			System.out.println("Como esperado");
		}
	}
	

	
}

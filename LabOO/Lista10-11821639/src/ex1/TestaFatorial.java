package ex1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestaFatorial {
	@Test
	public void testaNormal() {
		assertEquals(Fatorial.calculaFatorial(5), 120);
		assertEquals(Fatorial.calculaFatorial(0), 1);
	}
	
	@Test
	public void testaNegativo() {
		try {
			assertEquals(Fatorial.calculaFatorial(-10), 0);
		} catch (RuntimeException e) {
			System.out.println("Como esperado");
		}
	}
}

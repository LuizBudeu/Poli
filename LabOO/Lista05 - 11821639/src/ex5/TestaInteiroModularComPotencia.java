package ex5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

public class TestaInteiroModularComPotencia {

	InteiroModularComPotencia a;
		
	@Before
	public void setUp() {
		a = new InteiroModularComPotencia(5, 17);
	}
	
	@Test
	public void testaPrimo() {
		a.elevadoA(17);
		assertEquals(5, a.getValor() % 17);
		
		a = new InteiroModularComPotencia(3, 13);
		a.elevadoA(13);
		assertEquals(3, a.getValor() % 13);
		
		a = new InteiroModularComPotencia(7, 19);
		a.elevadoA(19);
		assertEquals(7, a.getValor() % 19);
		
	}
	
	@Test
	public void testaMNaoPrimo() {
		a.elevadoA(8);
		assertNotEquals(5, a.getValor() % 8);
	}
	
	@Test
	public void testaANaoPrimo() {
		a = new InteiroModularComPotencia(6, 14);
		a.elevadoA(14);
		assertNotEquals(6, a.getValor() % 14);
	}
	
	@Test
	public void testaRepeticoes() {
		a = new InteiroModularComPotencia(3, 13);
		
		long[] b = new long[13];
		InteiroModularComPotencia c;
		int i;
		
		for(i = 1; i < 13; i++) {
			b[i-1] = (long) (Math.pow(3, i) % 13);
		}
		
		int f = 0, g = 0, h = 0;
		for(i = 0; i < 13; i++) {
			if(b[i] == 3) {
				f++;
			}
			
			if(b[i] == 9) {
				g++;
			}
			
			if(b[i] == 1) {
				h++;
			}
		}
		
		assertEquals(f, 4);
		assertEquals(g, 4);
		assertEquals(g, 4);
	}
	
}

package ex3;

import org.junit.*;


import static org.junit.Assert.*;


public class TestaInteiroAritmetico {

	InteiroAritmetico n;
	InteiroAritmetico m;
		
	@Before
	public void setUp() {
		n = new InteiroAritmetico(10);
		m = new InteiroAritmetico(20);
	}
	
	@Test
	public void testaSoma() {
		n.someMeCom(m);
		assertEquals(30, n.getValor());
	}
	
	@Test
	public void testaSubtracao() {
		n.subtraiaDeMim(m);
		assertEquals(-10, n.getValor());
	}
	
	@Test
	public void testaMultiplicacao() {
		n.multipliqueMePor(m);
		assertEquals(200, n.getValor());
	}
	
	@Test
	public void testaDivisao() {
		n.dividaMePor(m);
		assertEquals(0, n.getValor());
	}
	
	@Test
	public void testaEquals() {
		assertFalse(n.equals(m));
	}
	
	@Test
	public void testaString() {
		assertEquals("valor: 10", n.toString());
	}
	
}

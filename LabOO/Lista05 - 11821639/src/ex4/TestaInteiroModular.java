package ex4;

import org.junit.*;

import static org.junit.Assert.*;

public class TestaInteiroModular {

	InteiroModular a;
	InteiroModular b;
		
	@Before
	public void setUp() {
		a = new InteiroModular(10, 2);
		b = new InteiroModular(50, 5);
	}
	
	@Test
	public void testaSoma() {
		a.someMeCom(b);
		assertEquals(0, a.getValor());
	}
	
	@Test
	public void testaSubtracao() {
		b.subtraiaDeMim(a);
		assertEquals(0, b.getValor());
	}
	
	@Test
	public void testaMultiplicacao() {
		a.multipliqueMePor(b);
		assertEquals(0, a.getValor());
	}
	
	@Test
	public void testaDivisao() {
		b.dividaMePor(a);
		assertEquals(0, b.getValor());
	}
	
	@Test
	public void testaEquals() {
		assertFalse(a.equals(b));
	}
	
	@Test
	public void testaString() {
		assertEquals("valor: 10, m: 2", a.toString());
	}
}

package ex3;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestaSet {
	MulticonjuntoSet<Integer> m1;
	MulticonjuntoSet<Integer> m2;
		
	@Before
	public void setUp() {
		m1 = new MulticonjuntoSet<Integer>();
		m2 = new MulticonjuntoSet<Integer>();
		
		m1.add(1);
		m1.add(5);
		m1.add(-10);
		
		m2.add(1);
		m2.add(5);
		m2.add(-10);
	}
	
	@Test
	public void testaAdd() {
		assertTrue(m1.list.contains(1));
		assertTrue(m1.list.contains(5));
		assertTrue(m1.list.contains(-10));
		
	}
	
	@Test
	public void testaEquals() {
		assertTrue(m1.equals(m2));
	}
	
	@Test 
	public void testaAddAll() {
		m2.add(20);
		m2.add(30);
		m2.add(40);
		
		m1.addAll(m2);
		assertTrue(m1.list.contains(20));
		assertTrue(m1.list.contains(30));
		assertTrue(m1.list.contains(40));
	}
}

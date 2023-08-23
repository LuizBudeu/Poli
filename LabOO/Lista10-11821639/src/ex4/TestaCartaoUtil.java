package ex4;

import static org.junit.Assert.*;


import org.junit.Test;

public class TestaCartaoUtil {
	
	@Test 
	public void testaChecaValidade() {
		assertTrue(CartaoUtil.checaValidade("03/2025"));
		assertFalse(CartaoUtil.checaValidade("03/2021"));
	}
	
	@Test 
	public void testaGetFormatado() {
		assertEquals(CartaoUtil.getFormatado("4000 1234 5678 9017"), "4000123456789017");
		assertNotEquals(CartaoUtil.getFormatado("4000 1234 5678 9017"), "3000123456789017");
	}
	
	@Test 
	public void testaChecaFormato() {
		assertTrue(CartaoUtil.checaFormato(1, "4000 1234 5678 9017"));
		assertFalse(CartaoUtil.checaFormato(2, "4000 1234 5678 9017"));
	}
	
	@Test 
	public void testaLuhn() {
		assertEquals(CartaoUtil.Luhn("4000 1234 5678 9017"), 0);
		assertNotEquals(CartaoUtil.Luhn("4000 1234 5678 9018"), 0);
	}
	
	@Test 
	public void testaValidar() {
		assertEquals(CartaoUtil.validar(1, "4000 1234 5678 9017", "03/2025"), "Cartão válido");
		assertEquals(CartaoUtil.validar(1, "4000 1234 5678 9017", "03/2021"), "Cartão inválido");
	}
}

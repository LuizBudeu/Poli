package ex1;



import static org.junit.Assert.assertEquals;


import org.junit.Test;


public class TestaEx1 {

	
	@Test 
	public void TGeraPau() {
		try {
			PrimeiraExcecao.geraPau();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Peguei");
		}
	}
	
	@Test 
	public void TPegaPau() {

		/* Fiz a comparação com String porque o Junit dá erro ao fazer
		 * comparação com o console, mesmo printando corretamente
		 */
		assertEquals("Exceção pega", PrimeiraExcecao.pegaPau());
		

	}
	
}

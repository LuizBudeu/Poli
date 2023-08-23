package ex4;

import org.junit.Test;

public class TestaCodigo {
	
	@Test 
	public void TCodigoNormal() {
		try {
			Codigo.recebeCodigo("matriz 0 23 8821");
		} catch (Exception e) {
			System.out.println("Deu ruim");
		}
		finally {
			System.out.println("Se não printou: 'Deu ruim', o teste do código normal deu certo");
		}
	}
	
	@Test 
	public void TSemMatriz() {
		try {
			Codigo.recebeCodigo("matris 0 23 8821");
		} catch (Exception e) {
			System.out.println("O teste sem 'matriz' deu erro como esperado");
		}
	}
	
	@Test 
	public void TNaoIntOuDouble() {
		try {
			Codigo.recebeCodigo("matris 7 23 8821");
		} catch (Exception e) {
			System.out.println("O teste não int ou double deu erro como esperado");
		}
	}
	
	@Test
	public void TNumLinhasNegativo(){
		try {
			Codigo.recebeCodigo("matris 1 -23 8821");
		} catch (Exception e) {
			System.out.println("O teste numLinhas negativo deu erro como esperado");
		}
	}
	
	@Test
	public void TNumLinhasZero(){
		try {
			Codigo.recebeCodigo("matris 1 0 8821");
		} catch (Exception e) {
			System.out.println("O teste numLinhas == 0 deu erro como esperado");
		}
	}
	
	@Test
	public void TNumColunasNegativo(){
		try {
			Codigo.recebeCodigo("matris 1 23 -21121");
		} catch (Exception e) {
			System.out.println("O teste numColunas negativo deu erro como esperado");
		}
	}
	
	@Test
	public void TNumColunasZero(){
		try {
			Codigo.recebeCodigo("matris 1 543 0");
		} catch (Exception e) {
			System.out.println("O teste numColunas == 0 deu erro como esperado");
		}
	}
	
}





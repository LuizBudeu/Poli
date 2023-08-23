/*
 * Os resultados do BigInteger demoram mais pois essa classe ocupa muita mais memória: seus métodos
 * e operações demoram mais por causa de seu tamanho. O BigInteger não preza eficiência, e sim espaço
 * para cálculos.
 */

import java.util.*;
import java.lang.*;


public class TesteTempo {
	private final static int TAMANHO = 100000;
	private final static int MAXIT = 10;
	private static int[] vint = new int[TAMANHO];
	
	public static void preenche() {
		for (int i = 0; i < TAMANHO; i++) {
			vint[i] = i;
		}
	}

	public static long testeint(int i) {
		long y = 0;
		long inicio = System.currentTimeMillis();
		for (int k = 0; k < MAXIT; k++)
			for (int j = 0; j < TAMANHO; j++) {
				y += vint[j]; // atribui a y a soma de 1 a TAMANHO-1
			}
		long fim = System.currentTimeMillis();
		System.out.println("int, teste:" +i + ":Tempo gasto: " + (fim - inicio) + "ms");
		return (fim - inicio);
	}
	
	public static void main(String[] args) {
		long min, med, soma;
		long aux;
		min = Integer.MAX_VALUE;
		soma = 0;
		preenche();
		System.out.println("Teste para 5 iteracoes");
		for (int i = 0; i < 5; i++) {
			aux = TesteTempo.testeint(i + 1);
			// os testes devem ser feitos um apos o outro 5 vezes
			if (aux < min)
			min = aux;
			soma += aux;
		}
		med = soma / 5;
		System.out.println("Resultados finais: tempo minimo = " +min + " tempo medio = " + med);
		}
}


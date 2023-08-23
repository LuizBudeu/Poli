package ex3;


/* O segundo código é melhor pois ao substituir o número
 * da constante gravitacional por uma variável, se quisermos
 * utilizar esse valor mais de uma vez basta só escrever
 * 'CONSTANTE_GRAVITACIONAL'. E caso queiramos mudar esse 
 * número em qualquer outra situação, em vez de ter que mudar
 * manualmente o número em cada instância, podemos só alterar
 * o valor da 'CONSTANTE_GRAVITACIONAL'. Também não é neces-
 * sário comentar o número no segundo código pois o nome já
 * é autoexplicativo.
 */


public class Testa2 {
	double energiaPotencial1(double massa, double altura) {
		// 9.81 ´e a constante gravitacional .
		return massa * 9.81 * altura;
	}

	static final double CONSTANTE_GRAVITACIONAL = 9.81;

	double energiaPotencial2(double massa, double altura) {
		return massa * CONSTANTE_GRAVITACIONAL * altura;
	}
}

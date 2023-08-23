package ex2;

/* Os comentários nesse código, além de terem os formatos
 * diferentes a cada instância, são inúteis nesse caso
 * pois descrevem o que o código faz de maneira mais 
 * complicada do que o próprio código. Por exemplo, não
 * é necessário comentar a linha "private int dayOfMonth;"
 * pois já é claro o que ela faz. Além disso o código em si
 * não faz nada, só existe uma variável 'dayOfMonth' que 
 * não é inicializada, e portanto é autoinicializada pelo
 * Java como 0, sendo que não existe dia 0 do mês.
 */

public class AnnualDateRule{
	
	/*
	* Construtor padr~ao .
	*/
	protected AnnualDateRule() {
	}

	/** Dia do m^es . */
	private int dayOfMonth;

	/**
	 * Retorna o dia do m^es.
	 *
	 * @return o dia do m^es
	 */
	public int getDayofMonth() {
		return dayOfMonth;
	}
	
	public static void main(String[] args) {
		AnnualDateRule annualDateRule;
		
		for(int i = 0; i < 10; i++) {
			annualDateRule = new AnnualDateRule();
			System.out.print(annualDateRule.getDayofMonth());
		}
		
	}
}

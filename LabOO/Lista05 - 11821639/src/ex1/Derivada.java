package ex1;

class Base {
	Base(int p) {
		System.out.println("Constroi Base");
	}
}

/* O erro ocorre pois quando o construtor de 'Derivada' chama o construtor de 'Base'
 * implicitamente, � necess�rio explicitar a chamada 'Base(1)' por meio de 'super(1)'
 * pois � necess�rio passar um par�metro.
 */


public class Derivada extends Base {
	Derivada () {
		super(0);
		System.out.println("Constroi Derivada");
	}
	
	public static void main(String []argc){
		Derivada obj = new Derivada();
	}
}

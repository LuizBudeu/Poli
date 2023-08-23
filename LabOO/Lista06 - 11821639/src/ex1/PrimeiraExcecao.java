package ex1;

public class PrimeiraExcecao {
	public static void geraPau(){
		throw new ArrayIndexOutOfBoundsException("Indice > 0");
	}
	
	public static String pegaPau() {
		try {
			geraPau();
			return "";
		} catch (Exception e) {
			return("Exceção pega");
		}

	}
}

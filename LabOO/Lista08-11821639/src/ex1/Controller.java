package ex1;

public class Controller {
	public static int calculaFatorial(int n){
		int i;
		int fat = 1;
		
		if (n >= 0) {
			for (i = n; i > 0; i--) {
				fat *= i;
			}
		}
		else {
			throw new RuntimeException();
		}
		
		return fat;
	}
}

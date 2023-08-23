package ex1;


public class Fatorial {
	
	static int calculaFatorial(int n) {
		int i;
		int fatorial = 1;
		
		if (n >= 0) {
			for (i = n; i > 0; i--) {
				fatorial *= i;
			}
		}
		else {
			throw new RuntimeException();
		}
		
		return fatorial;
	}
	
	
	public static void main(String args[]) {
		
		System.out.print(calculaFatorial(5));

		}
	}


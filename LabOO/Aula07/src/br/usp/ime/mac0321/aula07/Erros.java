package br.usp.ime.mac0321.aula07;

public class Erros {

	static void contas() throws ArithmeticException,ArrayIndexOutOfBoundsException {
		 	int den=1, num=10, indice=-1; 
		 	int c[]= {1,2};
		 	if (den == 0){
		    	throw new ArithmeticException("Denominador inválido");
		 	}
		 	int res = num / den;
		 	if (indice < 0 || indice >= c.length ){
		   		throw new ArrayIndexOutOfBoundsException("Indice bixado");
		 	}
		 	c[indice] = res;
	}
	
	public static void main(String args[]) {
		 	try{
		      		contas();
		 	}
		 	catch( ArithmeticException e){				 
		 		System.err.println( e );
		 	}
		 	catch( ArrayIndexOutOfBoundsException e){	
		 		System.err.println( e );
		 	}
	}
}

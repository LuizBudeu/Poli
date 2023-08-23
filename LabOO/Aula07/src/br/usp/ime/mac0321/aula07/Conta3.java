package br.usp.ime.mac0321.aula07;

public class Conta3 {
	    public static void main(String args[]){
	        int den=2, num=10, indice = 2;
	        int c[] = {1};
	        
	        try {  // Indicador do trecho crítico
	              int res = num / den;
	              try {
	            	  c[indice]=res;
	              }
	              catch(ArrayIndexOutOfBoundsException e){ 
	            	  	// Recuperação:  estouro de índice do vetor
	                  	System.err.println("Estourou o índice");      
	           			indice = 0;
	              }
	    	}
	    	catch(ArithmeticException e) { 
	    		// Recuperação da exceção aritmética
	            System.err.println("Dividiu por zero!");
	            den = 1;
	        }
	        System.out.println("indice = " + indice);
	   }
}

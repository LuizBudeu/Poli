package br.usp.ime.mac0321.aula07;

public class Conta2 {
	public static void main(String args[]){
	      int den=2, num=10, res=0;
	      int c[] = {1};
	     
	      try{ 
	         // Indicador do trecho crítico
	    	  res = num/den;
	       	  c[999] = res;
	      }
	      catch(ArithmeticException e) {
	          // Recuperação da exceção aritmética
	          System.err.println("Dividiu por zero!");
	          res = 1;
	      }
	      catch(ArrayIndexOutOfBoundsException e){ 
	          // Recuperação da exceção de estouro de índice de vetor
	          System.err.println("Estourou o índice");
	      }
	      System.out.println("res = " + res);
   }
}


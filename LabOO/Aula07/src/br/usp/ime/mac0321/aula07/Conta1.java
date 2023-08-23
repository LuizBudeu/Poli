package br.usp.ime.mac0321.aula07;

public class Conta1 {
	public static void main( String args[]){
       int den = 0;
       int num = 30, res;

       try{  // Indicador do trecho crítico
            res = num / den;
       }
       catch (ArithmeticException e) { //Recuperação da exceção
            System.err.println("Dividiu por zero!");
            res = 1;
       }
       System.out.println("res = " + res);
	}
}

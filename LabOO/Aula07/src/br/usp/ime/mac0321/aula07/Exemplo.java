package br.usp.ime.mac0321.aula07;

public class Exemplo {
	  	static void proc_exemplo(){
	     	try{
	       		// Tenta lançar instância de uma exceção aritmética 
	       		throw new ArithmeticException("Dividiu por zero fake");
	        }	
	  		catch(ArithmeticException e){
	             System.err.println("Erro no método: " + e.getMessage() );
	             throw e;  // Relança e para tratamento em outro escopo
	         }
	    }
	    public static void main(String args[]){
	    	try{
	    		Exemplo.proc_exemplo();
	       	}
	       	catch(ArithmeticException e){
	       		System.err.println("Retratamento da exceção: " + e );
	        }
	   	}
}

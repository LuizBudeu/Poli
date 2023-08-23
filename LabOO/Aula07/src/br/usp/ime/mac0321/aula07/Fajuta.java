package br.usp.ime.mac0321.aula07;

class ProprioErro extends Exception {
 	private int aux;

 	ProprioErro( int a){  //Construtor
    	aux = a;
 	}
 	public String toString(){ 
 		// devolve nome da classe e estado interno
    	return("ProprioErro[" + aux + "]: " + super.toString() );
 	}
} 

public class Fajuta {
	static void calcula(int a)
					throws ProprioErro {
  		if(a > 10)
   			throw new ProprioErro(a); // Lança instância nova classe
	}
	public static void main(String args[]){
   		try{
      		Fajuta.calcula(1);             // Não gera exceção
      		Fajuta.calcula(300);           // Gera exceção
   		}	
   		catch(ProprioErro e){ // Tratamento da nova exceção
 			System.err.println("Capturada " + e);
   		}
	}

}

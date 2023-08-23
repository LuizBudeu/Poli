package br.usp.ime.mac321.aula09;

class MeuGenerico<T>{  
	T treco;  
	void add(T t){
		this.treco=t;
	}  
	T get(){
		return treco;
	}  
} 

public class Generico3 {
	public static void main(String args[]){  
		MeuGenerico<Integer> m=new MeuGenerico<Integer>();  
		m.add(2);  
		//m.add("mfinger");//Compile time error  
		System.out.println(m.get());  
	}
}

package br.usp.ime.mac321.aula09;

/* Podemos usar um curinga como um tipo de parâmetro, campo, 
 * tipo de retorno ou variável local. No entanto, não é permitido 
 * usar um curinga como um argumento de tipo para uma chamada de 
 * método genérico, uma criação de instância de classe genérica ou 
 * um supertipo.
 */

import java.util.*;  
abstract class Forma{  
	abstract void desenha();  
} 

class Retangulo extends Forma{  
	void desenha(){System.out.println("desenhando um retângulo");}  
}

class Circulo extends Forma{  
	void desenha(){System.out.println("desenhando um círculo");}  
}

public class Generico5 {
	// Aceita derivados de Forma  
	public static void desenhaFormas(List<? extends Forma> lists){  
		for(Forma s:lists){  
			s.desenha();  //chama Forma pela instância derivada  
		}  
	}  
	public static void main(String args[]){  
		List<Retangulo> list1=new ArrayList<Retangulo>();  
		list1.add(new Retangulo());  
		  
		List<Circulo> list2=new ArrayList<Circulo>();  
		list2.add(new Circulo());  
		list2.add(new Circulo());
		
		// mistura círculos e retângulos
		List<Forma> list3=new ArrayList<Forma>();  
		list3.add(new Retangulo());
		list3.add(new Circulo());  
		  
		desenhaFormas(list1);  
		desenhaFormas(list2); 
		desenhaFormas(list3);
	}
}

/*
 * Nota: List<? super Integer>  aceita Integer e seus pais, como Number e Object
 *
 */
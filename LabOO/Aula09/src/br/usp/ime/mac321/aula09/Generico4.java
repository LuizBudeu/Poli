package br.usp.ime.mac321.aula09;

/*
 * Type parameters naming conventions:
 *
 *	T - Type
 *	E - Element
 *	K - Key
 *	N - Number
 *	V - Value
 * 
 */
public class Generico4 {
	public static < E > void printArray(E[] elements) {  
        for ( E element : elements){          
            System.out.println(element );  
         }  
         System.out.println();  
    }  
    public static void main( String args[] ) {  
        Integer[] intArray = { 10, 20, 30, 40, 50 };  
        Character[] charArray = { 'J', 'A', 'V', 'A', 'G','E','N','E','R','I', 'C' };  
  
        System.out.println( "Imprime Integer Array" );  
        printArray( intArray  );   
  
       System.out.println( "Imprime Character Array" );  
        printArray( charArray );   
    }   
}  


package br.usp.ime.mac321.aula09;
import java.util.*;  


public class Generico2 {
	public static void main(String args[]){  
		Map<Integer,String> map=new HashMap<Integer,String>();  
		map.put(1,"joão");  
		map.put(4,"maria");  
		map.put(2,"zezinho");  
		  
		//Use Map.Entry para Set e Iterator  
		Set<Map.Entry<Integer,String>> conjunto = map.entrySet();
		//o tipo retornado por map.entrySet() é Set<Map.Entry<Integer,String>>
		  
		Iterator<Map.Entry<Integer,String>> itr = conjunto.iterator();  
		while(itr.hasNext()){  
			Map.Entry e = itr.next(); //não precisa typecast  
			System.out.println(e.getKey()+" "+e.getValue());  
		} 
	}
		  
}

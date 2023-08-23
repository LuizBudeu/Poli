package br.usp.ime.mac321.aula09;

import java.util.*;  

public class Generico1 {
	public static void main(String args[]){  
		ArrayList<String> list=new ArrayList<String>();  
		list.add("marc");  
		list.add("elo");  
		//list.add(32);//compile time error  
		  
		String s=list.get(1);//type casting is not required  
		System.out.println("elemento 1: "+s);  
		  
		Iterator<String> itr=list.iterator();  
		while(itr.hasNext()) 
			System.out.println(itr.next());  
	}
}

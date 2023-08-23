package ex4;

import java.util.Iterator;

import ex3.MulticonjuntoArrayList;

public class mainEx4 {
	public static void main(String[] args) {
		MulticonjuntoArrayList<Integer> list = new MulticonjuntoArrayList<Integer>();
		list.add(10);
		list.add(20);
		list.add(30);
		
		ConjuntoAdaptado<Integer> conjuntoAdaptado = new ConjuntoAdaptado<>(list);
		
		conjuntoAdaptado.add(10);
		
		Iterator<Integer> itr = conjuntoAdaptado.list.iterator();  

		
		for(int i = 0; i < conjuntoAdaptado.list.size(); i++) {
			if(conjuntoAdaptado.list.get(i) == 10) {
				System.out.println("Tem 10"); 
			}
		}
		
	}
}

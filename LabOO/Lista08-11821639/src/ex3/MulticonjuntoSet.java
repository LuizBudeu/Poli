package ex3;

import java.util.HashSet;
import java.util.Set;

public class MulticonjuntoSet<T> {
	Set<T> list = new HashSet<T>();
		
	public void add(T element){
		list.add(element);
	} 
	
	public boolean equals(MulticonjuntoSet<T> m){

		return list.equals(m.list);
	}
	
	public void addAll(MulticonjuntoSet<T> m) {
		list.addAll(m.list);
	}
}


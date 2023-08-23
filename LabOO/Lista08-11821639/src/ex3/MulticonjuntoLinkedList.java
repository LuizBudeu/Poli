package ex3;

import java.util.LinkedList;

public class MulticonjuntoLinkedList<T> {
	LinkedList<T> list = new LinkedList<T>();
	
	public void add(T element){
		list.add(element);
	} 
	
	public boolean equals(MulticonjuntoLinkedList<T> m){

		if (m.list.size() != list.size())
			return false;
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) != m.list.get(i)){
				return false;
			}
		}
		
		return true;
	}
	
	public void addAll(MulticonjuntoLinkedList<T> m) {
		list.addAll(m.list);
		
	}
}

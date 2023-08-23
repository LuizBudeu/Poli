package ex3;


import java.util.Stack;

public class MulticonjuntoStack<T> {
	Stack<T> list = new Stack<T>();
	
	public void add(T element){
		list.add(element);
	} 
	
	public boolean equals(MulticonjuntoStack<T> m){

		if (m.list.size() != list.size())
			return false;
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) != m.list.get(i)){
				return false;
			}
		}
		
		return true;
	}
	
	public void addAll(MulticonjuntoStack<T> m) {
		list.addAll(m.list);
		
	}
}

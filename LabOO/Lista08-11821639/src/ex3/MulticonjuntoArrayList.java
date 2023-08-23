package ex3;

import java.util.ArrayList;


public class MulticonjuntoArrayList<T> {
	private ArrayList<T> list = new ArrayList<T>();
	
	public void add(T element){
		getList().add(element);
	} 
	
	public boolean equals(MulticonjuntoArrayList<T> m){

		if (m.getList().size() != getList().size())
			return false;
		
		for (int i = 0; i < getList().size(); i++) {
			if (getList().get(i) != m.getList().get(i)){
				return false;
			}
		}
		
		return true;
	}
	
	public void addAll(MulticonjuntoArrayList<T> m) {
		getList().addAll(m.getList());
		
	}

	public ArrayList<T> getList() {
		return list;
	}

	public void setList(ArrayList<T> list) {
		this.list = list;
	}
}

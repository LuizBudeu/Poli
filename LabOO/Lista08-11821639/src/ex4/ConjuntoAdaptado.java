package ex4;

import java.util.ArrayList;

import ex3.MulticonjuntoArrayList;

public class ConjuntoAdaptado<T> extends Conjunto<T> {
	
	ArrayList<T> list;
	
	public ConjuntoAdaptado(MulticonjuntoArrayList<T> m) {
		this.list = m.getList();
	}

}

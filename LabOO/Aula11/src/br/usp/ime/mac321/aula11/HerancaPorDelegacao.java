package br.usp.ime.mac321.aula11;

import java.util.Vector;

class HerancaPura<T> extends Vector<T> {
	public void push (T element) {
		insertElementAt(element, 0);
	}
	public T pop () {
		T result = firstElement ();
		removeElementAt (0);
		return result;
	}
}

public class HerancaPorDelegacao<T>  {
	Vector<T> vetorLocal = new Vector<T>(0);
	public void push (T element) {
		vetorLocal.insertElementAt(element, 0);
	}
	public T pop () {
		T result = vetorLocal.firstElement ();
		vetorLocal.removeElementAt (0);
		return result;
	}
}

package br.ime.usp.mac321.ep1.ex1;

class EventSet {
	private Evento[] events = new Evento[100];
	private int index = 0;
	private int next = 0;

	public void insere(Evento e) {
		if (index >= events.length)
			return;
		events[index++] = e;
	}

	public Evento getNext() {
		boolean looped = false;
		int start = next;
		do {
			next = (next + 1) % events.length;

			if (start == next)
				looped = true;

			if ((next == (start + 1) % events.length) && looped)
				return null;
		} while (events[next] == null);
		return events[next];
	}

	public void removeCurrent() {
		events[next] = null;
	}
}

public class Controlador {
	private EventSet es = new EventSet();

	public void insere(Evento c) {
		es.insere(c);
	}

	public void run() {
		Evento e;
		while ((e = es.getNext()) != null) {
			if (e.ready()) {
				e.acao();
				System.out.print(e.descricao());
				es.removeCurrent();
			}
		}
	}
}

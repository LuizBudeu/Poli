package br.ime.usp.mac321.ep1.ex1;

public abstract class Evento {
	private long evtTime;

	public Evento(long eventTime) {
		evtTime = eventTime;
	}

	public boolean ready() {
		return System.currentTimeMillis() >= evtTime;
	}

	abstract public void acao();

	abstract public String descricao();
}

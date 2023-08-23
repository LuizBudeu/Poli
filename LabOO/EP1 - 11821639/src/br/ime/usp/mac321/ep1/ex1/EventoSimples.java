package br.ime.usp.mac321.ep1.ex1;

public class EventoSimples extends Evento{

	public EventoSimples(long eventTime) {
		super(eventTime);
	}

	@Override
	public void acao() {
		System.out.print("Ação!");
		
	}

	@Override
	public String descricao() {
		return "Nada";
	}
	

}









package br.ime.usp.mac321.ep1.ex2;

public class Medico {
	private int frequencia;
	private Droga[] conjunto;
	private int duracao;
	
	public Medico(int f, Droga[] c, int d) {
		frequencia = f;
		conjunto = c;
		duracao = d;
	}
	
	public Droga[] getConjunto() {
		return conjunto;
	}
	
	public int getFrequencia() {
		return frequencia;
	}
	
	public int getDuracao() {
		return duracao;
	}
}

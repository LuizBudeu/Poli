package br.usp.ime.mac321.aula11;

import java.util.List;
import java.util.ListIterator;

public class ExtractMethodExample3 {
	List<Double> pedidos;
	String nome;
	void imprimeDivida () {
		imprimeCabecalho(); 
		double divida = calculaDivida();
		imprimeDetalhes(divida); // novo !
	}
	private double calculaDivida() {
		ListIterator<Double> it = pedidos.listIterator();
		double divida = 0.0;
		while (it.hasNext()){
			double valor = it.next();
			divida += valor;
		}
		return divida;
	}
	private void imprimeDetalhes(double divida) {
		System.out.println ("nome: " + nome);
		System.out.println ("divida total: " + divida);		
	}
	private void imprimeCabecalho() {
		System.out.println ("***************************");
		System.out.println ("*** Dívidas do Cliente ****");
		System.out.println ("***************************");		
	}
}

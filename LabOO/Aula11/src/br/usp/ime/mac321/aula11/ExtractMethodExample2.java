package br.usp.ime.mac321.aula11;

import java.util.List;
import java.util.ListIterator;

public class ExtractMethodExample2 {
	List<Double> pedidos;
	String nome;
	void imprimeDivida () {
		ListIterator<Double> it = pedidos.listIterator();
		double divida = 0.0;
		imprimeCabecalho(); 
		// calcula dívidas
		while (it.hasNext()){
			double valor = it.next();
			divida += valor;
		}
		imprimeDetalhes(divida); // novo !
	}
	void imprimeDetalhes(double divida) {
		System.out.println ("nome: " + nome);
		System.out.println ("divida total: " + divida);		
	}
	void imprimeCabecalho() {
		System.out.println ("***************************");
		System.out.println ("*** Dívidas do Cliente ****");
		System.out.println ("***************************");		
	}

}

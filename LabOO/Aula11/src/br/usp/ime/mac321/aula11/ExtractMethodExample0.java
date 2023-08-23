package br.usp.ime.mac321.aula11;

import java.util.List;
import java.util.ListIterator;

public class ExtractMethodExample0 {
	List<Double> pedidos;
	String nome;
	void imprimeDivida () {
		ListIterator<Double> it = pedidos.listIterator();
		double divida = 0.0;
		// imprime cabeçalho
		System.out.println ("***************************");
		System.out.println ("*** Dívidas do Cliente ****");
		System.out.println ("***************************");
		// calcula dívidas
		while (it.hasNext()){
			double valor = it.next();
			divida += valor;
		}
		// imprime detalhes
		System.out.println ("nome: " + nome);
		System.out.println ("divida total: " + divida);
		}
}

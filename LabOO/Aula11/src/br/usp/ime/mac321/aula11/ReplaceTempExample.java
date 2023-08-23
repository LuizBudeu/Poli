package br.usp.ime.mac321.aula11;

public class ReplaceTempExample {
	private double quantidade;
	private double precoItem;
	double getPreco() {
		double precoBase = quantidade * precoItem;
		double fatorDesconto;
		if (precoBase > 1000) 
			fatorDesconto = 0.95;
		else fatorDesconto = 0.98;
		return precoBase * fatorDesconto;
	}
	

	
	double getPrecoRefatorado1() {
		final double fatorDesconto;
		if (precoBase() > 1000) 
			fatorDesconto = 0.95;
		else fatorDesconto = 0.98;
		return precoBase() * fatorDesconto;
	}	
	private double precoBase() {
		return quantidade * precoItem;
	}


	
	
	
	double getPrecoRefatorado2() {
		return precoBase() * fatorDesconto();
	}	
	private double fatorDesconto() {
		return (precoBase() > 1000) ? 0.95 : 0.98;
	}

}

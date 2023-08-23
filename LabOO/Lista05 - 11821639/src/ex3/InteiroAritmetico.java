package ex3;

import ex2.NumeroAritmetico;

public class InteiroAritmetico extends NumeroAritmetico {
	
	public InteiroAritmetico(long v) {
		valor = v;
	}

	public long getValor() {
		return super.valor;
	}
	
	public InteiroAritmetico someMeCom(NumeroAritmetico n) {
		valor += ((InteiroAritmetico) n).getValor();
		return this;
	}

	public InteiroAritmetico subtraiaDeMim(NumeroAritmetico n) {
		valor -= ((InteiroAritmetico) n).getValor();
		return this;
	}

	public InteiroAritmetico multipliqueMePor(NumeroAritmetico n) {
		valor *= ((InteiroAritmetico) n).getValor();
		return this;
	}

	public InteiroAritmetico dividaMePor(NumeroAritmetico n) {
		valor /= ((InteiroAritmetico) n).getValor();
		return this;
	}
	
	public boolean equals(NumeroAritmetico n) {
		return mesmoValor(n);
	}
	
	public String toString() {
		return "valor: "+ valor;
	}
	
}

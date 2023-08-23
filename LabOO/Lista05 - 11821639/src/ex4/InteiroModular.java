package ex4;
import ex2.NumeroAritmetico;
import ex3.InteiroAritmetico; 

public class InteiroModular extends InteiroAritmetico{
	private long m;
	
	public InteiroModular(long n, long m) {
		super(n);
		this.m = m;
	}
	
	public long getM() {
		return m;
	}
	
	@Override
	public InteiroModular someMeCom(NumeroAritmetico n) {
		valor = super.someMeCom(n).getValor() % m;
		return this;
	}

	@Override
	public InteiroModular subtraiaDeMim(NumeroAritmetico n) {
		valor = super.subtraiaDeMim(n).getValor() % m;
		return this;
	}
	
	@Override
	public InteiroModular multipliqueMePor(NumeroAritmetico n) {
		valor = super.multipliqueMePor(n).getValor() % m;
		return this;
	}
	
	@Override
	public InteiroModular dividaMePor(NumeroAritmetico n) {
		valor = super.dividaMePor(n).getValor() % m;
		return this;
	}
	
	public boolean equals(InteiroModular n) {
		if (this.valor == n.getValor() && this.m == n.getM()) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		return "valor: "+ valor +", m: "+ m;
	}
}

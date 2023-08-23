package ex5;

import ex4.InteiroModular;

public class InteiroModularComPotencia extends InteiroModular {
	
	public InteiroModularComPotencia(long n, long m) {
		super(n, m);
	}

	public InteiroModularComPotencia elevadoA (long n) {
		int i;
		long j = this.valor;
		
		for (i = 1; i < n; i++) {
			this.valor *= j;
		}
		
		return this;
	}
	
}

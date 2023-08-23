package ex2;

public abstract class NumeroAritmetico {
	protected long valor;
	
	public final boolean mesmoValor(NumeroAritmetico n) {
		if (this.valor == n.valor)
			return true;
		return false;
	}
	
	abstract public NumeroAritmetico someMeCom(NumeroAritmetico n);
	
	abstract public NumeroAritmetico subtraiaDeMim(NumeroAritmetico n);
	
	abstract public NumeroAritmetico multipliqueMePor(NumeroAritmetico n);
	
	abstract public NumeroAritmetico dividaMePor(NumeroAritmetico n);

}

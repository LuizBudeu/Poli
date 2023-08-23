/*
	Ao criar v�rios objetos dessa classe, o garbage collector decide apagar aqueles que n�o est�o sendo 
	referenciados, resultando num n�mero limite de inst�ncias que podem ser criadas. Criar arrays de double
	pode ser custoso em rela��o � mem�ria, ent�o ao aumentar o tamanho desse array, o garbage collector
	permite um n�mero menor de inst�ncias para que n�o acabe a mem�ria.
*/

public class OcupaMemoria {
	static int quantos = 0;
	static boolean finalizou = false;
	double a[] = new double[100]; // apenas para ocupar espaco
	
	public OcupaMemoria(){
		quantos++;
	}
	
	protected void finalize() {
		if (!finalizou){
			System.out.println("Finalizou uma vez ap�os criar "+quantos+" objetos");
			finalizou = true; // n~ao imprime mais mensagens
		}
	}
	
	public static void teste(){
		while (OcupaMemoria.finalizou==false)
			new OcupaMemoria();
	}
}



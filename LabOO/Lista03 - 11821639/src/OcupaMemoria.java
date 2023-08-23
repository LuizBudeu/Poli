/*
	Ao criar vários objetos dessa classe, o garbage collector decide apagar aqueles que não estão sendo 
	referenciados, resultando num número limite de instâncias que podem ser criadas. Criar arrays de double
	pode ser custoso em relação à memória, então ao aumentar o tamanho desse array, o garbage collector
	permite um número menor de instâncias para que não acabe a memória.
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
			System.out.println("Finalizou uma vez ap´os criar "+quantos+" objetos");
			finalizou = true; // n~ao imprime mais mensagens
		}
	}
	
	public static void teste(){
		while (OcupaMemoria.finalizou==false)
			new OcupaMemoria();
	}
}



package ex4;


public class Codigo {
	public static void recebeCodigo(String codigo) throws Exception {
		if (!codigo.contains("matriz")) {
			throw new Exception();
		}
		
		if (codigo.charAt(7) != '0' && codigo.charAt(7) != '1') {
			throw new Exception();
		}
		
		String nLinha = "";
		int i;
		for(i = 9; codigo.charAt(i) != ' '; i++) {
			nLinha += codigo.charAt(i);
		}
		
		if (nLinha.contains("-") || nLinha.equals("0")) {
			throw new Exception();
		}
		
		String nColuna = codigo.substring(i+1, codigo.length());
		if (nColuna.contains("-") || nColuna.equals("0")) {
			throw new Exception();
		}

	}
}

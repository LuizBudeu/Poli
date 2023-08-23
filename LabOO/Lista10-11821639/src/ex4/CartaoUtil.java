package ex4;

import java.text.*;
import java.util.*;

public class CartaoUtil {
	public static final int VISA = 1;
	public static final int MASTERCARD = 2;
	public static final int AMEX = 3;
	public static final int DINERS = 4;
	public static final String CARTAO_OK = "Cartão válido";
	public static final String CARTAO_ERRO = "Cartão inválido";

	
	// A fim de testar o código os métodos serão static
	public static String validar(int bandeira, String numero, String validade) {
		
		if (!checaValidade(validade)) {
			return CARTAO_ERRO;

		} else {	
			if (!checaFormato(bandeira, numero)) {
				return CARTAO_ERRO;
				
			} else {
				if (Luhn(numero) == 0) {
					return CARTAO_OK;
					
				} else {
					return CARTAO_ERRO;
				}
			}
			
		}
	}
	public static boolean checaValidade(String validade) {
		// ----- VALIDADE -----
		Date dataValidade = null;
		try {
			dataValidade = new SimpleDateFormat("MM/yyyy").parse(validade);
		} catch (ParseException e) {
			System.out.println(CARTAO_ERRO);
			return false;
		}

		Calendar calValidade = new GregorianCalendar();
		calValidade.setTime(dataValidade);
		Calendar calHoje = new GregorianCalendar();

		return calHoje.before(calValidade);
	}
	
	public static String getFormatado(String numero) {
		String formatado = "";

		// remove caracteres não-numéricos
		for (int i = 0; i < numero.length(); i++) {
			char c = numero.charAt(i);
			if (Character.isDigit(c)) {
				formatado += c;
			}
		}
		
		return formatado;
	}

	public static boolean checaFormato(int bandeira, String numero) {
		// ---- PREFIXO E TAMANHO -----
		String formatado = getFormatado(numero);

		boolean formatoOK = false;
		switch (bandeira) {
		case VISA: // tamanhos 13 ou 16 , prefixo 4.
			if (formatado.startsWith("4") && (formatado.length() == 13 || formatado.length() == 16)) {
				formatoOK = true;
				
			} else {
				formatoOK = false;
			}
			break;
			
		case MASTERCARD: // tamanho 16 , prefixos 55
			if (formatado.startsWith("55") && formatado.length() == 16) {
				formatoOK = true;
				
			} else {
				formatoOK = false;
			}
			break;
			
		case AMEX: // tamanho 15 , prefixos 34 e 37.
			if ((formatado.startsWith("34") || formatado.startsWith("37")) && formatado.length() == 15) {
				formatoOK = true;
				
			} else {
				formatoOK = false;
			}
			break;
			
		case DINERS: // tamanho 14 , prefixos 300, 305 , 36 e 38 .
			if ((formatado.startsWith("300") || formatado.startsWith("305") || formatado.startsWith("36")
					|| formatado.startsWith("38")) && formatado.length() == 14) {
				formatoOK = true;
				
			} else {
				formatoOK = false;
			}
			break;
			
		default:
			formatoOK = false;
			break;
		}

		return formatoOK;
	}
	
	public static int Luhn(String numero) {
		
		String formatado = getFormatado(numero);
		int soma = 0;
		int digito = 0;
		int somafim = 0;
		boolean multiplica = false;
		for (int i = formatado.length() - 1; i >= 0; i--) {
			digito = Integer.parseInt(formatado.substring(i, i + 1));
			if (multiplica) {
				somafim = digito * 2;
				if (somafim > 9) {
					somafim -= 9;
				}
			} else {
				somafim = digito;
			}
			soma += somafim;
			multiplica = !multiplica;
		}
		return soma % 10;
	}
}

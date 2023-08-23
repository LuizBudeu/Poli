package br.ime.usp.mac321.ep1.ex4;

import br.ime.usp.mac321.ep1.ex1.Controlador;


class Medico{
	private int frequencia;
	private int duracao;
	
	public Medico(int f, int d) {
		frequencia = f;
		duracao = d;
	}
	
	public int getFrequencia() {
		return frequencia;
	}
	
	public int getDuracao() {
		return duracao;
	}
}


class Paciente{
	private double temperaturaBasal;
	private double concentracaoPAC;
	private double aumentoTemperatura;
	private int frequenciaSurtos;
	private double velocidadeAumentoPAC;
	private boolean vivo;
	
	public Paciente(double temperaturaBasal, double concentracaoPAC, double aumentoTemperatura, int frequenciaSurtos, double velocidadeAumentoPAC) {
		this.temperaturaBasal = temperaturaBasal;
		this.concentracaoPAC = concentracaoPAC;
		this.aumentoTemperatura = aumentoTemperatura;
		this.frequenciaSurtos = frequenciaSurtos;
		this.velocidadeAumentoPAC = velocidadeAumentoPAC;
		vivo = true;
	}
	
	public double getTemp(double t) {
		return temperaturaBasal + aumentoTemperatura;
	}
	
	public double getPAC(double c) {
		return concentracaoPAC;
	}
	
	public boolean taVivo() {
		return vivo;
	}
	
	public void mata() {
		vivo = false;
		temperaturaBasal *= -1;
		aumentoTemperatura *= -1;
		concentracaoPAC *= -1;
	}
	
	public double getTemperaturaBasal() {
		return temperaturaBasal;
	}
	
	public double getConcentracaoPAC() {
		return concentracaoPAC;
	}
	
	public int getFrequenciaSurtos() {
		return frequenciaSurtos;
	}
	
	public double getVelocidadeAumentoPAC() {
		return velocidadeAumentoPAC;
	}
	
	public double getAumentoTemperatura() {
		return aumentoTemperatura;
	}
}


public class Simulador2 extends Controlador{
	public static void main(String[] args) {
		
		
		//Não tive tempo de fazer, desculpe
		
		
	}
}

























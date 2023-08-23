package br.ime.usp.mac321.ep1.ex2;

public class Paciente {
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
